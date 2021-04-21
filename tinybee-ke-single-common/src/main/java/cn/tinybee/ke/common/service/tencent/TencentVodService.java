package cn.tinybee.ke.common.service.tencent;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vod.v20180717.VodClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @author hao.huang
 * @description
 * @date 2020/4/30
 */
@Slf4j
@Component
public class TencentVodService {

    private VodUploadClient client = null;
    private VodClient vodClient = null;
    private final static String region = "ap-shanghai";
    @PostConstruct
    public void init() {
        client = new VodUploadClient(TencentDeveloper.SECRET_ID, TencentDeveloper.SECRET_KEY);
        Credential cred = new Credential(TencentDeveloper.SECRET_ID, TencentDeveloper.SECRET_KEY);
        vodClient = new VodClient(cred, region);
    }

    public void upload() throws TencentCloudSDKException {
        String fileName = "D:/test.mp4";
//        VodUploadRequest request = new VodUploadRequest();
//        request.setMediaFilePath("D:/test.mp4");
//        request.setClassId(660235L);
////        request.
//        try {
//            VodUploadResponse response = client.upload("ap-shanghai", request);
//            log.info("Upload FileId = {}", response.getFileId());
//        } catch (Exception e) {
//            // 业务方进行异常处理
//            log.error("Upload Err", e);
//        }
        TencentVodService tencentVodService = new TencentVodService();
        tencentVodService.init();
        tencentVodService.upload(fileName);
//        ApplyUploadRequest applyUploadRequest = new ApplyUploadRequest();
//        String mediaType = FileUtils.getcontentType(fileName.substring(fileName.lastIndexOf(".")));
//        applyUploadRequest.setMediaType(mediaType);
//
//        applyUploadRequest.setMediaName("test");
//        applyUploadRequest.setClassId(660235L);
//        applyUploadRequest.setStorageRegion(region);
//
//        ApplyUploadResponse applyUploadResponse = tencentVodService.vodClient.ApplyUpload(applyUploadRequest);
//        System.out.println(applyUploadResponse);
    }

    public static void main(String[] args) throws TencentCloudSDKException {
        TencentVodService tencentVodService = new TencentVodService();
        tencentVodService.init();
//        tencentVodService.upload();

        tencentVodService.g();

    }




    public TencentVod upload(String mediaFilePath) {
        // 构造上传请求
        VodUploadRequest request = new VodUploadRequest();
        // 设置封面
        // vodUploadRequest.setCoverFilePath();
        // 设置视频地址
        request.setMediaFilePath(mediaFilePath);
        // 指定存储区域
        request.setStorageRegion("ap-shanghai");
        // 指定分片并发数
//        request.setConcurrentUploadNumber(5);
        request.setProcedure("tinybee-hls");
//        request.
        try {
            VodUploadResponse response = client.upload("ap-shanghai", request);
            TencentVod tencentVod = new TencentVod(response.getFileId(), response.getMediaUrl(), response.getCoverUrl(), response.getRequestId());
            log.info("Upload FileId = {}", response.getFileId());

            return tencentVod;
        } catch (Exception e) {
            // 业务方进行异常处理
            log.error("Upload Err", e);
        }
        return null;
    }

    public String g () {
        //w6tugCYHsTRXppg7T8Cp
        Integer AppId = 12;
        String FileId = "12";
        Long CurrentTime = System.currentTimeMillis() / 1000;
//        Long PsignExpire = System.currentTimeMillis() + 1589548067;
//        String UrlTimeExpire = "5ebe9423‬";
        String Key = "12";
        HashMap<String, String> urlAccessInfo = new HashMap<String, String>();
//        urlAccessInfo.put("t", UrlTimeExpire);

        try {
            Algorithm algorithm = Algorithm.HMAC256(Key);
            String token = JWT.create().withClaim("appId", AppId).withClaim("fileId", FileId)
                    .withClaim("currentTimeStamp", CurrentTime)
//                    .withClaim("expireTimeStamp", PsignExpire)
                    .withClaim("urlAccessInfo", urlAccessInfo).sign(algorithm);
            System.out.println( token);
        } catch (JWTCreationException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
        }
        return null;
    }

}
