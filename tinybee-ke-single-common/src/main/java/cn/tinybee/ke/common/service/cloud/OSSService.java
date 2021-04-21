package cn.tinybee.ke.common.service.cloud;

import cn.tinybee.ke.common.service.cloud.domain.OSSUploadResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/9/13 7:49
 */
public interface OSSService {

    OSSUploadResult upload(MultipartFile file, String dir);

    void getMetaInfo ();
}
