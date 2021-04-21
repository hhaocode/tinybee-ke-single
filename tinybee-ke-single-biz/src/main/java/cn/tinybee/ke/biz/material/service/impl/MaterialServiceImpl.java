package cn.tinybee.ke.biz.material.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsLive;
import cn.tinybee.ke.biz.cms.service.ICmsLiveService;
import cn.tinybee.ke.biz.material.dto.MaterialDto;
import cn.tinybee.ke.biz.material.entity.MaterialArticle;
import cn.tinybee.ke.biz.material.entity.MaterialFile;
import cn.tinybee.ke.biz.material.service.IMaterialArticleService;
import cn.tinybee.ke.biz.material.service.IMaterialFileService;
import cn.tinybee.ke.biz.material.service.IMaterialService;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/22 11:33
 */
@Service
public class MaterialServiceImpl implements IMaterialService {


    @Autowired
    private IMaterialFileService iMaterialFileService;

    @Autowired
    private IMaterialArticleService iMaterialArticleService;

    @Autowired
    private ICmsLiveService cmsLiveService;

    @Override
    public Page<MaterialDto> page(Operator user, Page page, Integer type, Long groupId, String name) {
        Page<MaterialDto> res = new Page<>();
        switch (type) {
            case 1:
            case 2:
            case 3:
            case 4:
                Page<MaterialFile> filePage = iMaterialFileService.page(page, type, groupId, name);
                res = filePage(filePage, type);
                break;
            case 5:
                Page<MaterialArticle> articlePage = iMaterialArticleService.page(user, page, name);
                res = articlePage(articlePage, type);
                break;
            case 6:
                CmsLive liveParam = new CmsLive();
                liveParam.setTitle(name);
                Page<CmsLive> cmsLivePage = cmsLiveService.pageQuery(page, liveParam);
                res = livePage(cmsLivePage, type);
                break;
            default:
                res = new Page<>();
                break;

        }
        return res;
    }


    private Page<MaterialDto> filePage (Page<MaterialFile> param, Integer type) {
        Page<MaterialDto> res = new Page<>(param.getCurrent(), param.getSize(), param.getTotal());
        List<MaterialDto> collect = param.getRecords().stream().map(v -> {
            MaterialDto materialDto = new MaterialDto();
            materialDto.setId(v.getId());
            materialDto.setType(type);
            materialDto.setFile(v);
            materialDto.setCreateTime(v.getCreateTime());
            materialDto.setSize(v.getSize());
            materialDto.setTitle(StringUtils.isNotBlank(v.getTitle()) ? v.getTitle() : v.getName());
            materialDto.setUrl(v.getFileUrl());
            materialDto.setDuration(v.getVodDuration());
            return materialDto;
        }).collect(Collectors.toList());
        res.setRecords(collect);
        return res;
    }

    private Page<MaterialDto> articlePage (Page<MaterialArticle> param, Integer type) {
        Page<MaterialDto> res = new Page<>(param.getCurrent(), param.getSize(), param.getTotal());
        List<MaterialDto> collect = param.getRecords().stream().map(v -> {
            MaterialDto materialDto = new MaterialDto();
            materialDto.setId(v.getId());
            materialDto.setType(type);
            materialDto.setArticle(v);
            materialDto.setCreateTime(v.getCreateTime());
            materialDto.setTitle(v.getTitle());
            return materialDto;
        }).collect(Collectors.toList());
        res.setRecords(collect);
        return res;
    }

    private Page<MaterialDto> livePage (Page<CmsLive> param, Integer type) {
        Page<MaterialDto> res = new Page<>(param.getCurrent(), param.getSize(), param.getTotal());
        List<MaterialDto> collect = param.getRecords().stream().map(v -> {
            MaterialDto materialDto = new MaterialDto();
            materialDto.setId(v.getId());
            materialDto.setType(type);
            materialDto.setLive(v);
            materialDto.setCreateTime(v.getCreateTime());
            materialDto.setTitle(v.getTitle());
            materialDto.setStartTime(v.getStartTime());
            materialDto.setEndTime(v.getEndTime());
            return materialDto;
        }).collect(Collectors.toList());
        res.setRecords(collect);
        return res;
    }
}
