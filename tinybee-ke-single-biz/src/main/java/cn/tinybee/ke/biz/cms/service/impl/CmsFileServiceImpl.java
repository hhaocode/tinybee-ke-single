package cn.tinybee.ke.biz.cms.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.tinybee.ke.biz.cms.entity.CmsFile;
import cn.tinybee.ke.biz.cms.entity.CmsImage;
import cn.tinybee.ke.biz.cms.entity.CmsVod;
import cn.tinybee.ke.biz.cms.mapper.CmsFileMapper;
import cn.tinybee.ke.biz.cms.mapper.CmsImageMapper;
import cn.tinybee.ke.biz.cms.service.ICmsFileService;
import cn.tinybee.ke.biz.cms.service.ICmsImageService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.enums.FileTypeEnum;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.service.cloud.OSSService;
import cn.tinybee.ke.common.service.cloud.domain.OSSUploadResult;
import cn.tinybee.ke.common.util.PageUtils;
import cn.tinybee.ke.common.util.file.FileUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * oss上文件 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-09-29
 */
@Service
public class CmsFileServiceImpl extends ServiceImpl<CmsFileMapper, CmsFile> implements ICmsFileService {


    @Autowired
    private CmsFileMapper cmsFileMapper;


    @Autowired
    private CmsImageMapper cmsImageMapper;

    @Autowired
    private OSSService ossService;

    @Autowired
    private ICmsImageService iCmsImageService;


    @Transactional
    @Override
    public CmsFile upload(MultipartFile[] files, FileTypeEnum type) {

//        String contentType = file.getContentType();
        System.out.println(files);
        // 如果是视频 和音频  则将要上传到视频点播
        List<CmsFile> res = new ArrayList<>();
        for (MultipartFile file : files) {
            buildCmsFile(file, type);
        }

        return null;
    }

    @Override
    public CmsVod uploadVod(MultipartFile file) {


        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<CmsImage> uploadImage(MultipartFile[] files) throws IOException {

        List<CmsImage> res = new ArrayList<>();
        for (MultipartFile file : files) {
            CmsImage cmsImage = new CmsImage();
            cmsImage.setContentType(file.getContentType());
            String fileName = file.getOriginalFilename();
            cmsImage.setFileName(fileName);
            cmsImage.setExtension(FileUtil.extName(fileName));
//            FileUtil.getM
            cmsImage.setMd5(FileUtils.getMd5(file.getInputStream()));
            OSSUploadResult images = ossService.upload(file, "images");
            cmsImage.setFileUrl(images.getUrl());
            cmsImageMapper.insert(cmsImage);
            res.add(cmsImage);
        }
        return res;
    }

    @Override
    public Page<CmsFile> imagePage(PageParam param) {
        Page page = PageUtils.pageConvert(param);
        QueryWrapper<CmsImage> queryWrapper = new QueryWrapper<>();
        return iCmsImageService.page(page, queryWrapper);
    }

    private void doUpload () {

    }


    private CmsFile buildCmsFile (MultipartFile file, FileTypeEnum type) {
        String originalFilename = file.getOriginalFilename(); // 原文件名
//        String type = FileTypeUtil.getType(originalFilename);
//        String type = FileTypeUtil.getType(file.getInputStream());
        if (type == null) {
            throw new BusinessException("未知文件类型");
        }
        //String s = URLConnection.guessContentTypeFromStream(file.getInputStream());
        CmsFile cmsFile = new CmsFile();


        cmsFile.setAvailable(true);
        cmsFile.setCreateTime(LocalDateTime.now());
        cmsFile.setCreator(0L);
        cmsFile.setFileName(originalFilename);
        cmsFile.setSuffix(FileUtil.extName(originalFilename));
        // 获取文件 是否

        cmsFile.setContentType(file.getContentType());
        cmsFile.setType(type);
        cmsFile.setSize(file.getSize());
//        cmsFile.setFileId();// 上传之后的数据


        cmsFileMapper.insert(cmsFile);


        return cmsFile;

    }

    private CmsFile checkVod (MultipartFile file) {
        return null;
    }
}
