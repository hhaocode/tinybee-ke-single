package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsVod;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 视频信息 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-07-28
 */
public interface ICmsVodService extends IService<CmsVod> {


    /**
     * 上传
     * @param file
     * @param module
     * @return
     */
    CmsVod upload(MultipartFile file,  CmsVod cmsVod);

    /**
     *
     * @param cmsVod
     */
    void updateTranscoded(CmsVod cmsVod);


    /**
     *
     * @param file
     * @return
     */
    CmsVod uploadVideo(MultipartFile file, Long operatorId);


    CmsVod uploadAudio(MultipartFile file, Long operatorId);
}
