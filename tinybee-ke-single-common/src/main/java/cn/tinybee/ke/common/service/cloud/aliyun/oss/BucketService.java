package cn.tinybee.ke.common.service.cloud.aliyun.oss;

import cn.tinybee.ke.common.config.properties.Aliyun;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CreateBucketRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/16 12:35
 */
@Slf4j
@Component
public class BucketService {

    @Autowired
    private Aliyun aliyun;

    @PostConstruct
    private OSS oossClient() {
        OSS ossClient = new OSSClientBuilder().build(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
        return ossClient;
    }

    /**
     *
     * @return
     */
    public List<Bucket> buckets () {
        OSS ossClient = oossClient();
        try {
            return ossClient.listBuckets();
        }catch (Exception e) {
            log.info("获取Bucket列表失败");
        } finally {
            ossClient.shutdown();
        }
        return Collections.emptyList();
    }

    public boolean exists (String bucketName) {
        OSS ossClient = oossClient();
        try{
            return ossClient.doesBucketExist(bucketName);
        } catch (Exception e) {
            log.info("判断Bucket是否存在失败");
        } finally {
            ossClient.shutdown();
        }
        return false;
    }

    /**
     *
     * @param bucketName
     * @return
     */
    public boolean createBucket(String bucketName) {
        OSS ossClient = oossClient();
        CreateBucketRequest createBucketRequest = new CreateBucketRequest("<yourBucketName>");
        // 如果创建存储空间的同时需要指定存储类型以及数据容灾类型, 可以参考以下代码。
        // 此处以设置存储空间的存储类型为标准存储为例。
        // createBucketRequest.setStorageClass(StorageClass.Standard);
        // 默认情况下，数据容灾类型为本地冗余存储，即DataRedundancyType.LRS。如果需要设置数据容灾类型为同城冗余存储，请替换为DataRedundancyType.ZRS。
        // createBucketRequest.setDataRedundancyType(DataRedundancyType.ZRS)
        // 创建存储空间。
        try {
            boolean b = ossClient.doesBucketExist(bucketName);
            if (!b) {
                ossClient.createBucket(createBucketRequest);
            }
        }catch (Exception e) {
            log.info("创建Bucket失败{}", e.getMessage());
            return false;
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return true;
    }

}
