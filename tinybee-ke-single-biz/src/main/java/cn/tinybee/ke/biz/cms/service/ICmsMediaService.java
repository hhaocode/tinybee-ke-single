package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsMedia;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 视频信息 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-06-07
 */
public interface ICmsMediaService extends IService<CmsMedia> {

    Long upload(MultipartFile file, Integer module);

    void updateTranscoded(CmsMedia cmsMedia);
}
