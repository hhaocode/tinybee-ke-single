package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsOss;
import cn.tinybee.ke.biz.cms.mapper.CmsOssMapper;
import cn.tinybee.ke.biz.cms.service.ICmsOssService;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.service.cloud.OSSService;
import cn.tinybee.ke.common.service.cloud.domain.OSSUploadResult;
import cn.tinybee.ke.common.util.file.FileUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 素材文件表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-21
 */
@Service
public class CmsOssServiceImpl extends ServiceImpl<CmsOssMapper, CmsOss> implements ICmsOssService {


    @Autowired
    private OSSService ossService;

    @Value("${oss.dir:oss-dir}")
    private String ossDir;

    @Override
    public List<CmsOss> upload(Operator operator, MultipartFile[] files, Integer type, Integer purpose) {
        List<CmsOss> res = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (MultipartFile file : files) {
            // 获取Md5
            String md5 = null;
            try {
                md5 = FileUtils.getMd5(file.getInputStream());
            } catch (IOException e) {
                throw new BusinessException("文件上传失败");
            }
            QueryWrapper<CmsOss> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("md5", md5);
            queryWrapper.eq("deleted", false);
            List<CmsOss> list = this.list(queryWrapper);
            if (!list.isEmpty()) {
                CmsOss cmsOss = list.get(0);
                cmsOss.setId(null);
                cmsOss.setCreator(operator.getId());
                cmsOss.setModifier(operator.getId());
                cmsOss.setCreateTime(now);
                cmsOss.setModifyTime(now);
                cmsOss.setDeleted(false);
                this.save(cmsOss);
                res.add(cmsOss);
                continue;
            }
            OSSUploadResult upload = ossService.upload(file, ossDir);
            if (upload == null) {
                continue;
            }
            CmsOss cmsOss = new CmsOss();
            cmsOss.setName(file.getOriginalFilename());
            cmsOss.setETag(upload.getETag());
            cmsOss.setFileUid(upload.getFileUid());
            cmsOss.setChannel(upload.getChannel());
            cmsOss.setType(type);
            cmsOss.setPurpose(purpose);
            cmsOss.setExtension(upload.getExtension());
            cmsOss.setContentType(file.getContentType());
            cmsOss.setUrl(upload.getUrl());
            cmsOss.setSize(upload.getSize());
            cmsOss.setCreator(operator.getId());
            cmsOss.setModifier(operator.getId());
            cmsOss.setCreateTime(now);
            cmsOss.setModifyTime(now);
            cmsOss.setDeleted(false);
            this.save(cmsOss);
            res.add(cmsOss);
        }
        return res;
    }

    @Override
    public List<CmsOss> listByType(Operator operator, Integer type, Integer purpose) {
        QueryWrapper<CmsOss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.eq("delete", false);
        if (purpose != null) {
            queryWrapper.eq("purpose", purpose);
        }
        return this.list(queryWrapper);
    }

    @Override
    public Page<CmsOss> page(Operator user, Page page, Integer type, Integer purpose) {
        QueryWrapper<CmsOss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.eq("delete", false);
        if (purpose != null) {
            queryWrapper.eq("purpose", purpose);
        }
        return this.page(page, queryWrapper);
    }
}
