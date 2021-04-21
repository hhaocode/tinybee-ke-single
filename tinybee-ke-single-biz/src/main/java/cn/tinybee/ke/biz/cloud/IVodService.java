package cn.tinybee.ke.biz.cloud;

import cn.tinybee.ke.biz.cms.entity.CmsVod;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/3 12:00
 */
public interface IVodService {

    /**
     *
     * @param file
     * @return
     */
    CmsVod upload(MultipartFile file, CmsVod cmsVod);
}
