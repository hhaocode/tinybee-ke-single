package cn.tinybee.ke.biz.material.service.impl;

import cn.tinybee.ke.biz.material.dto.MaterialDto;
import cn.tinybee.ke.biz.material.entity.*;
import cn.tinybee.ke.biz.material.mapper.MaterialFileMapper;
import cn.tinybee.ke.biz.material.service.IMaterialFileService;
import cn.tinybee.ke.biz.material.service.IMaterialGroupService;
import cn.tinybee.ke.biz.material.service.IMaterialVodUploadService;
import cn.tinybee.ke.common.entity.GroupCount;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.service.cloud.OSSService;
import cn.tinybee.ke.common.service.cloud.VodService;
import cn.tinybee.ke.common.service.cloud.domain.OSSUploadResult;
import cn.tinybee.ke.common.service.cloud.domain.VodPlayAuthResult;
import cn.tinybee.ke.common.util.AssertUtils;
import cn.tinybee.ke.common.util.file.FileUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-23
 */
@Service
public class MaterialFileServiceImpl extends ServiceImpl<MaterialFileMapper, MaterialFile> implements IMaterialFileService {

    @Autowired
    private OSSService ossService;

    @Autowired
    private IMaterialGroupService iMaterialGroupService;


    @Autowired
    private VodService vodService;

    @Autowired
    private IMaterialVodUploadService iMaterialVodUploadService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<MaterialFile> upload(MultipartFile[] files, int type, Long currentUserId) {
        check(files, type);
        List<MaterialFile> resList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String dir = (type == 1 ? "image/" : "file/") + now.format(dateTimeFormatter);
        for (MultipartFile file : files) {
            // 获取Md5
            String md5 = null;
            try {
                md5 = FileUtils.getMd5(file.getInputStream());
            } catch (IOException e) {
                throw new BusinessException("文件上传失败");
            }
            QueryWrapper<MaterialFile> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("md5", md5);
            queryWrapper.eq("deleted", false);
            queryWrapper.eq("type", type);
            MaterialFile dbFile = this.getOne(queryWrapper);
            if (dbFile != null) {
                dbFile.setId(null);
                dbFile.setType(type);
                dbFile.setName(file.getOriginalFilename());
                dbFile.setCreator(currentUserId);
                dbFile.setModifier(currentUserId);
                dbFile.setCreateTime(now);
                dbFile.setModifyTime(now);
                dbFile.setDeleted(false);
                this.save(dbFile);
                resList.add(dbFile);
                continue;
            }
            OSSUploadResult upload = ossService.upload(file, dir);
            if (upload == null) {
                continue;
            }
            MaterialFile materialFile = new MaterialFile();
            materialFile.setType(type);

            materialFile.setFileUid(upload.getFileUid()); // 自动生成唯一文件名
            materialFile.setOssId(upload.getETag());
            materialFile.setMd5(md5);
            materialFile.setFileUrl(upload.getUrl());
            materialFile.setFilePath(upload.getFilePath());
            // 分组  分类
            materialFile.setGroupId(0L);
            // 文件信息
            materialFile.setName(file.getOriginalFilename());
            materialFile.setExtension(upload.getExtension());
            materialFile.setContentType(file.getContentType());
            // title  intro
            materialFile.setSize(upload.getSize());
            // 上传人信息
            materialFile.setCreator(currentUserId);
            materialFile.setModifier(currentUserId);
            materialFile.setCreateTime(now);
            materialFile.setModifyTime(now);
            materialFile.setDeleted(false);
            this.save(materialFile);
            resList.add(materialFile);
        }
        return resList;
    }

    private void check (MultipartFile[] files, int type) {
        switch (type) {
            case 1:
                for (MultipartFile file : files) {
                    AssertUtils.isTrue(file.getContentType().startsWith("image"), "文件中有不是图片类型的文件，请重新选择上传");
                }
                break;
            case 4:
                break;
            default:
                throw new BusinessException("上传类型无效");
        }
    }

    @Override
    public Map search(Page page, Long groupId, String name, int type, Long currentUserId) {
        Map<String, Object> res = new HashMap<>();
        // 统计每组的数据
        Map<Long, GroupCount> groupCountMap = baseMapper.groupingStatisticsCnt(type).stream().collect(Collectors.toMap(GroupCount::getId, Function.identity()));
        QueryWrapper<MaterialFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        long cnt = this.count(queryWrapper);
        res.put("cnt", cnt);

        List<GroupCount> groupCounts = iMaterialGroupService.listByType(type, true).stream().map(v -> {
            GroupCount groupCount = null;
            if (v.getId() == null) {
                groupCount = new GroupCount();
                groupCount.setId(v.getId());
                groupCount.setCnt(cnt);
                groupCount.setName(v.getName());
                return groupCount;
            }
            groupCount =  groupCountMap.get(v.getId());
            if (groupCount == null) {
                groupCount = new GroupCount();
                groupCount.setId(v.getId());
                GroupCount groupCnt = groupCountMap.get(v.getId());
                groupCount.setCnt(groupCnt  != null ? groupCnt.getCnt() : 0);
            }
            groupCount.setName(v.getName());
            return groupCount;
        }).collect(Collectors.toList());
        res.put("groupCnt", groupCounts);


        QueryWrapper<MaterialFile> conditionWrapper = new QueryWrapper<>();
        conditionWrapper.eq("type", type);
        if (groupId != null) {
            conditionWrapper.eq("group_id", groupId);
        }
        if (StringUtils.isNotBlank(name)) {
            conditionWrapper.like("name", name);
        }
        res.put("page", this.page(page, conditionWrapper));
        return res;
    }

    @Override
    public Page<MaterialFile> page(Page page, int type, Long groupId, String name) {
        QueryWrapper<MaterialFile> queryWrapper = new QueryWrapper<>();
        if (groupId != null) {
            queryWrapper.eq("group_id", groupId);
        }
        queryWrapper.eq("type", type);
        queryWrapper.eq("deleted", false);
        return this.page(page, queryWrapper);
    }

    @Override
    public Boolean deleteBatch(Operator operator, List<Long> ids) {
        if (ids.isEmpty()) {
            throw new BusinessException("未选择任何数据，删除失败");
        }
        return this.removeByIds(ids);
    }

    @Override
    public MaterialFile saveOrModifyVod(MaterialFile param, Long currentUserId) {
        AssertUtils.notNull(param.getVodUploadId(), "请先上传");
        if (param.getId() == null) {
            param.setDeleted(false);
            param.setCreateTime(LocalDateTime.now());
            param.setCreator(currentUserId);
        }
        param.setModifyTime(LocalDateTime.now());
        param.setModifier(currentUserId);
        MaterialVodUpload upload = iMaterialVodUploadService.getById(param.getVodUploadId());
        AssertUtils.notNull(upload, "请先上传有效文件");
//        param.setFileUid(upload.get);
//        param.setOssId(upload.getO);
        param.setMd5(upload.getMd5());
//        param.setFileUrl(upload.getU);
//        param.setFilePath(upload.get);
//        param.setGroupId();
//        param.setClassifyId();
        param.setName(upload.getName());
        param.setExtension(upload.getExtension());
        param.setContentType(upload.getContentType());
//        param.setTitle();
//        param.setIntro();
        param.setSize(upload.getSize());
//        param.setType();
        param.setVodId(upload.getVid());
        VodPlayAuthResult.MetaInfo videoInfo = iMaterialVodUploadService.info(param.getVodUploadId());
        if (StringUtils.isBlank(param.getVodThumbUrl())) {
            param.setVodThumbUrl(videoInfo.getCoverUrl());
        }
        param.setVodDuration(new BigDecimal(videoInfo.getDuration()));
        this.saveOrUpdate(param);
        return param;
    }

    @Override
    public Page<MaterialFile> pageQuery(Page page, Integer type, String title, List<Long> groupIds) {

        QueryWrapper<MaterialFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", false);
        if (type != null) {
            queryWrapper.eq("type", type);
        }
        if (StringUtils.isNotBlank(title)) {
            queryWrapper.like("title", title);
        }
        if (groupIds != null && !groupIds.isEmpty()) {
            queryWrapper.in("group_id", groupIds);
        }
        return this.page(page, queryWrapper);
    }


    @Override
    public VodPlayAuthResult getVodPlayAuth(Long id, boolean auth) {
        MaterialFile materialFile = this.getById(id);
        AssertUtils.notNull(materialFile, "无有效点播信息");
//        MaterialVodUpload upload = iMaterialVodUploadService.getById(id);
        VodPlayAuthResult videoPlayAuth = null;
        if (auth) {
            videoPlayAuth = vodService.getVideoPlayAuth(materialFile.getVodId());
        } else {
            videoPlayAuth = vodService.getVideoPlayUrl(materialFile.getVodId());
        }
        videoPlayAuth.setVid(materialFile.getVodId());
        videoPlayAuth.setMediaType(materialFile.getContentType().split("/")[0]);
        return videoPlayAuth;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteById(Operator operator, Long id) {
        MaterialFile file = this.getById(id);
        AssertUtils.notNull(file, "不存在的媒体文件");
        file.setModifyTime(LocalDateTime.now());
        file.setModifier(operator.getId());
        file.setDeleted(true);
        return this.updateById(file);
    }
}
