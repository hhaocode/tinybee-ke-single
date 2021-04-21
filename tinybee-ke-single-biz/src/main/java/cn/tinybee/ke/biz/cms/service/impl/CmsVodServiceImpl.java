package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsVod;
import cn.tinybee.ke.biz.cms.mapper.CmsVodMapper;
import cn.tinybee.ke.biz.cms.service.ICmsVodService;
import cn.tinybee.ke.common.enums.VodType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * <p>
 * 视频点播 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-09-29
 */
@Service
public class CmsVodServiceImpl extends ServiceImpl<CmsVodMapper, CmsVod> implements ICmsVodService {


//    private C

//    @Autowired
//    private IVodService iVodService;

    @Override
    public CmsVod upload(MultipartFile file, CmsVod cmsVod) {
        // 音视频上传
        cmsVod = new CmsVod();
        cmsVod.setFilename(file.getOriginalFilename());
//        iVodService.upload(file, cmsVod);
        if (cmsVod.getId() == null) {
            cmsVod.setCreateTime(LocalDateTime.now());
            cmsVod.setCreator(0L);
        }
        cmsVod.setModifyTime(LocalDateTime.now());
        cmsVod.setModifier(0L);
        this.saveOrUpdate(cmsVod);
        return cmsVod;
    }

    @Override
    public void updateTranscoded(CmsVod cmsVod) {

    }

    @Override
    public CmsVod uploadVideo(MultipartFile file, Long operatorId) {
        return upload(file, operatorId, VodType.video);
    }

    @Override
    public CmsVod uploadAudio(MultipartFile file, Long operatorId) {
        return upload(file, operatorId, VodType.audio);
    }

    private CmsVod upload (MultipartFile file, Long operatorId, VodType vodType) {
        // 上传文件
        CmsVod cmsVod = new CmsVod();
        cmsVod.setFilename(file.getOriginalFilename());
//        iVodService.upload(file, cmsVod);
        cmsVod.setVodType(vodType);
        cmsVod.setCreator(operatorId);
        cmsVod.setCreateTime(LocalDateTime.now());
        cmsVod.setModifyTime(LocalDateTime.now());
        cmsVod.setModifier(operatorId);
        this.save(cmsVod);
        return cmsVod;
    }
}
