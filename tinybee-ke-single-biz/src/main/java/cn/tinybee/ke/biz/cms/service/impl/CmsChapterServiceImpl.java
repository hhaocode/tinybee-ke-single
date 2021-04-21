package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.dto.CmsChapterPeriodsDto;
import cn.tinybee.ke.biz.cms.dto.CmsPeriodDto;
import cn.tinybee.ke.biz.cms.entity.CmsChapter;
import cn.tinybee.ke.biz.cms.entity.CmsPeriod;
import cn.tinybee.ke.biz.cms.entity.CmsPeriodVod;
import cn.tinybee.ke.biz.cms.mapper.CmsChapterMapper;
import cn.tinybee.ke.biz.cms.mapper.CmsPeriodMapper;
import cn.tinybee.ke.biz.cms.service.ICmsChapterService;
import cn.tinybee.ke.biz.cms.service.ICmsPeriodService;
import cn.tinybee.ke.biz.cms.service.ICmsPeriodVodService;
import cn.tinybee.ke.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 章节表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
@Service
public class CmsChapterServiceImpl extends ServiceImpl<CmsChapterMapper, CmsChapter> implements ICmsChapterService {

    @Autowired
    @Lazy
    private ICmsPeriodService ICmsPeriodService;

    @Autowired
    private ICmsPeriodVodService iCmsPeriodVodService;


    @Autowired
    private Mapper mapper;

    @Override
    public boolean delete(Long id) {
        //
        QueryWrapper<CmsPeriod> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", id);
        int count = ICmsPeriodService.count(queryWrapper);
        if (count > 0) {
            throw new BusinessException("该章节下面有内容，不能删除");
        }
        return this.removeById(id);
    }

    @Override
    public List<CmsChapterPeriodsDto> listChapterContentItemByContentId(Long contentId, Boolean requireVod) {
        requireVod = requireVod == null ? false : requireVod;
        QueryWrapper<CmsChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", contentId);
        List<CmsChapterPeriodsDto> cmsChapterPeriodsDtos = this.list(queryWrapper).stream().map(v -> mapper.map(v, CmsChapterPeriodsDto.class)).collect(Collectors.toList());
        Map<Long, List<CmsPeriodDto>> longListMap = ICmsPeriodService.mapItemByContentId(contentId);



        cmsChapterPeriodsDtos.forEach(v -> {
            List<CmsPeriodDto> cmsPeriodDtos = longListMap.get(v.getId());
            if (cmsPeriodDtos == null) {
                cmsPeriodDtos = Collections.emptyList();
            }
            v.setChildren(cmsPeriodDtos);
        });
        if (requireVod) {
            Map<Long, List<CmsPeriodVod>> periodIdListMap = iCmsPeriodVodService.listVodsByCourseId(contentId).stream().collect(Collectors.groupingBy(CmsPeriodVod::getPeriodId));
            cmsChapterPeriodsDtos.forEach(v -> {
                List<CmsPeriodDto> children = v.getChildren();
                children.forEach(d -> {
                    List<CmsPeriodVod> cmsPeriodVods = periodIdListMap.get(d.getId());
                    if (cmsPeriodVods == null) {
                        cmsPeriodVods = Collections.emptyList();
                    }
//                    d.setCmsPeriodVods(cmsPeriodVods);
                });
            });
        }
        return cmsChapterPeriodsDtos;
    }

    @Override
    public List<CmsChapter> chaptersByCourseId(Long courseId) {
        QueryWrapper<CmsChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.orderByAsc("sort");
        return this.list(queryWrapper);
    }
}
