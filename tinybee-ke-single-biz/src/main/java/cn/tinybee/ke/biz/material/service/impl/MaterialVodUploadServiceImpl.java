package cn.tinybee.ke.biz.material.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.tinybee.ke.biz.material.entity.MaterialVodUpload;
import cn.tinybee.ke.biz.material.mapper.MaterialVodUploadMapper;
import cn.tinybee.ke.biz.material.service.IMaterialVodUploadService;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.enums.VodType;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.service.cloud.VodService;
import cn.tinybee.ke.common.service.cloud.domain.VodPlayAuthResult;
import cn.tinybee.ke.common.service.cloud.domain.VodUploadResult;
import cn.tinybee.ke.common.util.file.FileUtils;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 素材音视频表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-22
 */
@Service
@Slf4j
public class MaterialVodUploadServiceImpl extends ServiceImpl<MaterialVodUploadMapper, MaterialVodUpload> implements IMaterialVodUploadService {

    @Autowired
    private VodService vodService;

    @Override
    public MaterialVodUpload upload(Operator operator, MultipartFile file, int type) {
        // md5 作为唯一码存在
        // 获取MD5
        String md5 = null;
        try {
            md5 = FileUtils.getMd5(file.getInputStream());
        } catch (IOException e) {
            throw new BusinessException("文件流错误,上传失败");
        }
        // 根据md5获取已上传的数据
        LocalDateTime now = LocalDateTime.now();
        QueryWrapper<MaterialVodUpload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        queryWrapper.eq("deleted", false);
        MaterialVodUpload materialVodUpload = this.getOne(queryWrapper);
        List<MaterialVodUpload> list = this.list(queryWrapper);
        String extension = FileUtil.extName(file.getOriginalFilename());
        if (materialVodUpload != null) {
            // 已经存在则构建一条新的
            materialVodUpload.setName(file.getOriginalFilename());
            materialVodUpload.setType(type);
            materialVodUpload.setExtension(extension);
            materialVodUpload.setContentType(file.getContentType());
            materialVodUpload.setId(null);
            materialVodUpload.setCreator(operator.getId());
            materialVodUpload.setModifier(operator.getId());
            materialVodUpload.setDeleted(false);
            materialVodUpload.setCreateTime(now);
            materialVodUpload.setModifyTime(now);
            this.save(materialVodUpload);
            return materialVodUpload;
        }
        VodUploadResult upload = vodService.upload(file, type);
        MaterialVodUpload materialVod = new MaterialVodUpload();
        // 基础信息
        materialVod.setMd5(md5);
        materialVod.setName(file.getOriginalFilename());

        materialVod.setVid(upload.getVid());
        materialVod.setChannel(upload.getChannel());
        materialVod.setType(type);

        materialVod.setSize(file.getSize());
        materialVod.setExtension(extension);
        materialVod.setContentType(file.getContentType());

        materialVod.setCreator(operator.getId());
        materialVod.setModifier(operator.getId());
        materialVod.setDeleted(false);
        materialVod.setCreateTime(now);
        materialVod.setModifyTime(now);
        this.save(materialVod);
        return materialVod;
    }

    /**
     * 获取新的
     * @param id
     * @return
     */
    @Override
    public VodPlayAuthResult.MetaInfo info(Long id) {
        MaterialVodUpload dbInfo = this.getById(id);
        try {
            return vodService.getVideoInfo(dbInfo.getVid());
        } catch (ClientException e) {
            return new VodPlayAuthResult.MetaInfo();
        }
    }
}
