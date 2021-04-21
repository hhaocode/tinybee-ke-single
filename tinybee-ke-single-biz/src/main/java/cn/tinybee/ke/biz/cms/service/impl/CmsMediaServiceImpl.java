package cn.tinybee.ke.biz.cms.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.tinybee.ke.biz.cms.entity.CmsMedia;
import cn.tinybee.ke.biz.cms.mapper.CmsMediaMapper;
import cn.tinybee.ke.biz.cms.service.ICmsMediaService;
import cn.tinybee.ke.biz.cms.service.ICmsVodService;
import cn.tinybee.ke.common.config.properties.TinybeeVideoProperties;
import cn.tinybee.ke.common.exception.BusinessException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;

/**
 * <p>
 * 视频信息 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-06-07
 */
@Service
public class CmsMediaServiceImpl extends ServiceImpl<CmsMediaMapper, CmsMedia> implements ICmsMediaService {

    @Autowired
    private TinybeeVideoProperties tinybeeVideoProperties;

    @Autowired
    private ICmsVodService iCmsVodService;


    @Transactional
    @Override
    public Long upload(MultipartFile file, Integer module) {
        // 获取存储方式 阿里云  腾讯云  本地存储

        // 中间路径 当前时间  yyyyy / MM / DD
        String prefixDir = LocalDate.now().toString().replaceAll("[-]", "/") + "/";
        // 若上传源目录不存在 则创建目录
        String targetDir = tinybeeVideoProperties.getUploadDir() + prefixDir;
//        FileUtils.mkdirs(targetDir);
        //文件信息
        // 原始文件名
        String fileName = file.getOriginalFilename();
        // 扩展名
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        String fileId = IdUtil.randomUUID();
        // 目标文件ID
        String targetFilename = fileId + "." + extension;
        try {
//            CmsVod cmsVod = new CmsVod();
//            cmsVod.setExtension(extension);
//            cmsVod.setMediaNo(fileId);
//            cmsVod.setModule(module);
//            cmsVod.setName(fileName);
//            cmsVod.setPrefixDir(prefixDir);
//            cmsVod.setSourceType(1);
//            cmsVod.setTranscoded(false);
//            cmsVod.setType(1);
//            iCmsVodService.save(cmsVod);
            // TODO 需要重新实现
            file.transferTo(new File(targetDir + targetFilename));
//            return cmsVod.getId();
            return null;
        } catch (Exception e) {
            throw new BusinessException("上传失败" + e.getLocalizedMessage());
        }
    }

    @Transactional
    @Override
    public void updateTranscoded(CmsMedia cmsMedia) {
        cmsMedia.setTranscoded(true);
        this.updateById(cmsMedia);
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM");
        String fileName = "12321321321.png";
        System.out.println(fileName.substring(fileName.lastIndexOf(".")));
    }
}
