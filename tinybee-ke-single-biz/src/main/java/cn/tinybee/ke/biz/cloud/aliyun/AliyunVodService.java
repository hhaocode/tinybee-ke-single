package cn.tinybee.ke.biz.cloud.aliyun;

import cn.tinybee.ke.biz.cloud.IVodService;
import cn.tinybee.ke.biz.cloud.entity.VodUploadParameter;
import cn.tinybee.ke.biz.cms.entity.CmsVod;
import cn.tinybee.ke.biz.cms.service.ICmsVodService;
import cn.tinybee.ke.common.config.properties.Aliyun;
import cn.tinybee.ke.common.exception.BusinessException;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * @author hao.huang
 * @version v1.0.0
 * @Package : cn.tinybee.ke.ke.biz.cloud.aliyun
 * @Description : TODO
 * @Create on : 2020/7/10 09:53
 **/
//@Component("aliyunVodService")
//    @Service
public class AliyunVodService implements IVodService {

    @Autowired
    private Aliyun aliyun;

    @Autowired
    private ICmsVodService iCmsVodService;

    private void upload() throws ClientException {
        //您的AccessKeyId
//        if (aliyun)
        System.out.println(aliyun);
        String accessKeyId = aliyun.getAccessKeyId();
        //您的AccessKeySecret
        String accessKeySecret = aliyun.getAccessKeySecret();
        //需要上传到VOD的本地视频文件的完整路径，需要包含文件扩展名
        String localFile = "d:/test.mp4";

        DefaultAcsClient vodClient = initVodClient(accessKeyId, accessKeySecret);
        VodUploadParameter parameter = new VodUploadParameter();
        parameter.setFilename("test.mp4");
        parameter.setTitle("测试视频");
        CreateUploadVideoResponse createUploadVideoResponse = createUploadVideo(vodClient, parameter);

        // 执行成功会返回VideoId、UploadAddress和UploadAuth
        String videoId = createUploadVideoResponse.getVideoId();

        JSONObject uploadAuth = JSONObject.parseObject(decodeBase64(createUploadVideoResponse.getUploadAuth()));
        JSONObject uploadAddress = JSONObject.parseObject(decodeBase64(createUploadVideoResponse.getUploadAddress()));

        // 使用UploadAuth和UploadAddress初始化OSS客户端
        OSSClient ossClient = initOssClient(uploadAuth, uploadAddress);

        // 上传文件，注意是同步上传会阻塞等待，耗时与文件大小和网络上行带宽有关
        uploadFile(ossClient, uploadAddress, new File(localFile));
        System.out.println("Put local file succeed, VideoId : " + videoId);
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


    /**
     * 初始化客户端
     * @param accessKeyId  开发者ID
     * @param accessKeySecret 开发者密钥
     * @return
     * @throws ClientException
     */
    private DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
        // 点播服务接入区域，国内请填cn-shanghai，其他区域请参考文档 https://help.aliyun.com/document_detail/98194.html
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    private DefaultAcsClient initVodClient () {
        return initVodClient(aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
    }

    /**
     * 获取视频上传地址和凭证
     * @param vodClient
     * @return
     * @throws ClientException
     */
    private CreateUploadVideoResponse createUploadVideo(DefaultAcsClient vodClient, VodUploadParameter parameter) throws ClientException {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setFileName(parameter.getFilename());
        request.setTitle(parameter.getTitle());
        request.setDescription(parameter.getDescription());
        //request.setTags("tag1,tag2");  标签
        //request.setCoverURL("http://vod.aliyun.com/test_cover_url.jpg"); 封面
        request.setCateId(1000158233L);   // 分类
//        request.setTemplateGroupId("64b7b60a4e1ffd9112626e68ad01ef2b");   // 模板ID
        request.setWorkflowId("dcea828e976522cb3371ed40ef8ca5da");    // 任务流ID  转码
        //request.setStorageLocation("");   // 存储位置
        //request.setAppId("app-1000000");
//        request.set
        //设置请求超时时间
        request.setSysReadTimeout(1000);
        request.setSysConnectTimeout(1000);
        return vodClient.getAcsResponse(request);
    }

    /**
     * 初始化OSS客户端
     * @param uploadAuth
     * @param uploadAddress
     * @return
     */
    private static OSSClient initOssClient(JSONObject uploadAuth, JSONObject uploadAddress) {
        String endpoint = uploadAddress.getString("Endpoint");
        String accessKeyId = uploadAuth.getString("AccessKeyId");
        String accessKeySecret = uploadAuth.getString("AccessKeySecret");
        String securityToken = uploadAuth.getString("SecurityToken");
        OSSClientBuilder ossClientBuilder = new OSSClientBuilder();
        return (OSSClient) ossClientBuilder.build(endpoint, accessKeyId, accessKeySecret, securityToken);
    }

    private void uploadFile(OSSClient ossClient, JSONObject uploadAddress, File file) {
        String bucketName = uploadAddress.getString("Bucket");
        String objectName = uploadAddress.getString("FileName");
        ossClient.putObject(bucketName, objectName, file);
    }


    @Async
    void uploadFile(OSSClient ossClient, JSONObject uploadAddress, InputStream in) {
        String bucketName = uploadAddress.getString("Bucket");
        String objectName = uploadAddress.getString("FileName");
        ossClient.putObject(bucketName, objectName, in);
    }


    private RefreshUploadVideoResponse refreshUploadVideo(DefaultAcsClient vodClient) throws ClientException {
        RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setVideoId("VideoId");

        //设置请求超时时间
        request.setSysReadTimeout(1000);
        request.setSysConnectTimeout(1000);

        return vodClient.getAcsResponse(request);
    }


    public GetVideoPlayAuthResponse getVideoPlayAuth( String videoId) throws Exception {
        //DefaultAcsClient client,
        String accessKeyId = aliyun.getAccessKeyId();
        //您的AccessKeySecret
        String accessKeySecret = aliyun.getAccessKeySecret();
        //需要上传到VOD的本地视频文件的完整路径，需要包含文件扩展名
        String localFile = "d:/test.mp4";

        DefaultAcsClient vodClient = initVodClient(accessKeyId, accessKeySecret);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        return vodClient.getAcsResponse(request);
    }

    @Override
    public CmsVod upload(MultipartFile file, CmsVod cmsVod) {
        // 初始化客户端
        DefaultAcsClient vodClient = initVodClient();
        // 构造上传参数
        VodUploadParameter parameter = new VodUploadParameter();
        parameter.setFilename(cmsVod.getFilename()); //源文件名
        parameter.setTitle(cmsVod.getFilename()); // 文件名
        CreateUploadVideoResponse createUploadVideoResponse = null;
        try {
            createUploadVideoResponse = createUploadVideo(vodClient, parameter);
        } catch (ClientException e) {
            throw new BusinessException("阿里云点播服务客户端初始化失败");
        }
        // 执行成功会返回VideoId、UploadAddress和UploadAuth
        String videoId = createUploadVideoResponse.getVideoId();
        JSONObject uploadAuth = JSONObject.parseObject(decodeBase64(createUploadVideoResponse.getUploadAuth()));
        JSONObject uploadAddress = JSONObject.parseObject(decodeBase64(createUploadVideoResponse.getUploadAddress()));
        // 使用UploadAuth和UploadAddress初始化OSS客户端
        OSSClient ossClient = initOssClient(uploadAuth, uploadAddress);
        // 上传文件，注意是同步上传会阻塞等待，耗时与文件大小和网络上行带宽有关
        try {
            uploadFile(ossClient, uploadAddress, file.getInputStream());
            cmsVod.setCreateTime(LocalDateTime.now());
            cmsVod.setChannel("aliyun");
            cmsVod.setRequestId(createUploadVideoResponse.getRequestId());
//            cmsVod.setUploadAddress(createUploadVideoResponse.getUploadAddress());
//            cmsVod.setUploadAuth(createUploadVideoResponse.getUploadAuth());
            cmsVod.setVid(videoId);
        } catch (IOException e) {
            throw new BusinessException("阿里云点播服务上传文件失败");
        }
        System.out.println("Put local file succeed, VideoId : " + videoId);
        return cmsVod;
    }
}
