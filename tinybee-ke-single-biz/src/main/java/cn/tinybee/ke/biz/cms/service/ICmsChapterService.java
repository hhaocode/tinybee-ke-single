package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.dto.CmsChapterPeriodsDto;
import cn.tinybee.ke.biz.cms.entity.CmsChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 章节表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
public interface ICmsChapterService extends IService<CmsChapter> {

    boolean delete(Long id);

    List<CmsChapterPeriodsDto> listChapterContentItemByContentId(Long contentId, Boolean requireVod);

    List<CmsChapter> chaptersByCourseId (Long courseId);
}
