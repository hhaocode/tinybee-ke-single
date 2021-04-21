package cn.tinybee.ke.common.service.cloud;

import cn.tinybee.ke.common.config.properties.Aliyun;
import cn.tinybee.ke.common.service.cloud.domain.OSSUploadResult;
import cn.tinybee.ke.common.util.IdUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Date;
import java.util.Random;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/9/13 7:51
 */
@Slf4j
@Component
public class AliyunOSSService implements OSSService {

    @Autowired
    private Aliyun aliyun;


    @Override
    public OSSUploadResult upload(MultipartFile file, String dir) {
        // TODO 缓存中获取配置
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        Random random = new Random();
        String fileName = IdUtils.fileNameId() + "." + extension;
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            OSS ossClient = new OSSClientBuilder().build(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
            PutObjectResult putResult = ossClient.putObject(aliyun.getBucketName(), dir + "/" + fileName, file.getInputStream(), objectMetadata);
//            putResult.get
            OSSUploadResult ossUploadResult = new OSSUploadResult();
            ossUploadResult.setETag(putResult.getETag());
            ossUploadResult.setFileUid(fileName);
            ossUploadResult.setUrl(getUrl(dir + "/" + fileName));
            ossUploadResult.setChannel("aliyun");
            ossUploadResult.setExtension(extension);
            ossUploadResult.setSize(file.getSize());
            ossUploadResult.setFilePath(dir + "/" + fileName);
            return ossUploadResult;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
//            throw new BusinessException("文件上传失败:" + e.getMessage());
            return null;
        }
    }

    @Override
    public void getMetaInfo() {
        OSS ossClient = new OSSClientBuilder().build(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
//        ossClient.generatePresignedUrl()
//        OSSObject object = ossClient.getObject(bucketName, "file/1319539859458756608.png");
//        ObjectMetadata objectMetadata = ossClient.getObjectMetadata(bucketName, "file/1319539859458756608.png");
//        System.out.println(object.getResponse().getUri());
//        System.out.println(objectMetadata);
    }

    public String getUrl (String key) {
        OSS ossClient = new OSSClientBuilder().build(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
        OSSObject object = ossClient.getObject(aliyun.getBucketName(), key);
        return object.getResponse().getUri();
    }


    public String getUrl(String key, Date expiration) {
        // 设置URL过期时间为100年  3600l* 1000*24*365*10 * 10
//        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10 * 10);
        // 生成URL
        OSS ossClient = new OSSClientBuilder().build(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
        URL url = ossClient.generatePresignedUrl(aliyun.getBucketName(), key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}
