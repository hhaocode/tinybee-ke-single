package cn.tinybee.ke.common.service.cloud;

import cn.tinybee.ke.common.enums.VodType;
import cn.tinybee.ke.common.service.cloud.domain.VodPlayAuthResult;
import cn.tinybee.ke.common.service.cloud.domain.VodUploadResult;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/26 9:49
 */
public interface VodService {

    VodUploadResult upload(MultipartFile file, Integer type);


    VodPlayAuthResult getVideoPlayAuth(String videoId);

    VodPlayAuthResult getVideoPlayUrl(String videoId);


    VodPlayAuthResult.MetaInfo getVideoInfo(String videoId) throws ClientException;

}
