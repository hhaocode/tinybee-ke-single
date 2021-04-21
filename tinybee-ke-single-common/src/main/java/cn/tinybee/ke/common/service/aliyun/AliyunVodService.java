package cn.tinybee.ke.common.service.aliyun;

import cn.tinybee.ke.common.config.properties.Aliyun;
import cn.tinybee.ke.common.enums.VodType;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.service.cloud.VodService;
import cn.tinybee.ke.common.service.cloud.domain.VodPlayAuthResult;
import cn.tinybee.ke.common.service.cloud.domain.VodUploadResult;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author hao.huang
 * @version v1.0.0
 * @Package : cn.tinybee.common.service.aliyun
 * @Description : TODO
 * @Create on : 2020/6/24 13:02
 **/
@Component
public class AliyunVodService implements VodService {

    @Autowired
    private Aliyun aliyun;

    private DefaultAcsClient initVodClient() {
        // 点播服务接入区域，国内请填cn-shanghai，其他区域请参考文档 https://help.aliyun.com/document_detail/98194.html
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    private static String decodeBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            Base64 decoder = new Base64();
            try {
                b = decoder.decode(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
            }
        }
        return result;
    }

    PutObjectResult uploadFile(OSSClient ossClient, JSONObject uploadAddress, InputStream in) {
        String bucketName = uploadAddress.getString("Bucket");
        String objectName = uploadAddress.getString("FileName");
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, in);
        ossClient.shutdown();
        return putObjectResult;
    }

    private static OSSClient initOssClient(JSONObject uploadAuth, JSONObject uploadAddress) {
        String endpoint = uploadAddress.getString("Endpoint");
        String accessKeyId = uploadAuth.getString("AccessKeyId");
        String accessKeySecret = uploadAuth.getString("AccessKeySecret");
        String securityToken = uploadAuth.getString("SecurityToken");
        OSSClientBuilder ossClientBuilder = new OSSClientBuilder();
        return (OSSClient) ossClientBuilder.build(endpoint, accessKeyId, accessKeySecret, securityToken);
    }


    @Override
    public VodUploadResult upload(MultipartFile file, Integer type) {
        // 初始化客户端
        DefaultAcsClient vodClient = initVodClient();
        // 构造上传参数
        CreateUploadVideoResponse createUploadVideoResponse = null;
        try {
            CreateUploadVideoRequest request = new CreateUploadVideoRequest();
            request.setFileName(file.getOriginalFilename());
            request.setTitle(file.getOriginalFilename());
//        request.setDescription(parameter.getDescription());
            //request.setTags("tag1,tag2");  标签
            //request.setCoverURL("http://vod.aliyun.com/test_cover_url.jpg"); 封面
            request.setCateId(aliyun.getCateId());   // 分类
//        request.setTemplateGroupId("64b7b60a4e1ffd9112626e68ad01ef2b");   // 模板ID
            request.setWorkflowId(aliyun.getWorkflowId());    // 任务流ID  转码
            //request.setStorageLocation("");   // 存储位置
            //request.setAppId("app-1000000");
//        request.set
            //设置请求超时时间
//            request.setSysReadTimeout(1000);
//            request.setSysConnectTimeout(1000);
            createUploadVideoResponse = vodClient.getAcsResponse(request);
            // 执行成功会返回VideoId、UploadAddress和UploadAuth
            String videoId = createUploadVideoResponse.getVideoId();
            JSONObject uploadAuth = JSONObject.parseObject(decodeBase64(createUploadVideoResponse.getUploadAuth()));
            JSONObject uploadAddress = JSONObject.parseObject(decodeBase64(createUploadVideoResponse.getUploadAddress()));
            // 使用UploadAuth和UploadAddress初始化OSS客户端
            OSSClient ossClient = initOssClient(uploadAuth, uploadAddress);
            // 上传文件，注意是同步上传会阻塞等待，耗时与文件大小和网络上行带宽有关
            uploadFile(ossClient, uploadAddress, file.getInputStream());
            vodClient.shutdown();
            GetVideoInfoResponse.Video video = getVideoInfo(initVodClient(), videoId).getVideo();
            VodUploadResult result = new VodUploadResult();
            result.setSize(video.getSize());
            result.setDuration(new BigDecimal(video.getDuration()));
            result.setVid(videoId);
            result.setChannel("aliyun");
            result.setCoverURL(video.getCoverURL());
            return result;
        } catch (Exception e) {
            throw new BusinessException("阿里云点播服务客户端初始化失败");
        }
    }

    @Override
    public VodPlayAuthResult getVideoPlayAuth(String videoId) {
        DefaultAcsClient vodClient = initVodClient();
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        try {
            GetVideoPlayAuthResponse acsResponse = vodClient.getAcsResponse(request);
            VodPlayAuthResult result = new VodPlayAuthResult();

            result.setRequestId(acsResponse.getRequestId());
            result.setPlayAuth(acsResponse.getPlayAuth());

            GetVideoPlayAuthResponse.VideoMeta videoMeta = acsResponse.getVideoMeta();

            VodPlayAuthResult.MetaInfo metaInfo = result.getMetaInfo();
            if (metaInfo == null) {
                metaInfo = new VodPlayAuthResult.MetaInfo();
                result.setMetaInfo(metaInfo);
            }
            metaInfo.setCoverUrl(videoMeta.getCoverURL());
            metaInfo.setDuration(videoMeta.getDuration());
            metaInfo.setStatus(videoMeta.getStatus());
            metaInfo.setTitle(videoMeta.getTitle());
            metaInfo.setVideoId(videoMeta.getVideoId());
            return result;
        } catch (ClientException e) {
            throw new BusinessException("获取阿里云点播权限失败");
        }
    }

    @Override
    public VodPlayAuthResult getVideoPlayUrl(String videoId) {
        DefaultAcsClient vodClient = initVodClient();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            GetPlayInfoRequest request = new GetPlayInfoRequest();
            request.setVideoId(videoId);
            request.setFormats("m3u8");
            response = vodClient.getAcsResponse(request);
            VodPlayAuthResult result = new VodPlayAuthResult();
            result.setRequestId(response.getRequestId());
            VodPlayAuthResult.MetaInfo metaInfo = result.getMetaInfo();
            if (metaInfo == null) {
                metaInfo = new VodPlayAuthResult.MetaInfo();
                result.setMetaInfo(metaInfo);
            }
            GetPlayInfoResponse.VideoBase videoBase = response.getVideoBase();
            metaInfo.setCoverUrl(videoBase.getCoverURL());
            metaInfo.setDuration(Float.valueOf(videoBase.getDuration()));
            metaInfo.setStatus(videoBase.getStatus());
            metaInfo.setTitle(videoBase.getTitle());
            metaInfo.setVideoId(videoBase.getVideoId());

            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                result.setUrl(playInfo.getPlayURL());
                return result;
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
            return result;
        } catch (Exception e) {
            throw new BusinessException("获取阿里云点播权限失败");
        }
    }

    @Override
    public VodPlayAuthResult.MetaInfo getVideoInfo(String videoId) throws ClientException {
        // 初始化客户端
        DefaultAcsClient vodClient = initVodClient();
        GetVideoInfoResponse.Video video = getVideoInfo(vodClient, videoId).getVideo();
        VodPlayAuthResult.MetaInfo res = new VodPlayAuthResult.MetaInfo();
        String coverURL = video.getCoverURL();
        res.setCoverUrl(coverURL != null ? coverURL.split("[?]")[0] : null);
        res.setDuration(video.getDuration());
        res.setStatus(video.getStatus());
        res.setTitle(video.getTitle());
        res.setVideoId(videoId);
        return res;
    }

    private GetVideoInfoResponse getVideoInfo(DefaultAcsClient client, String vid) throws ClientException {
        GetVideoInfoRequest request = new GetVideoInfoRequest();
        request.setVideoId(vid);
        return client.getAcsResponse(request);
    }

}
