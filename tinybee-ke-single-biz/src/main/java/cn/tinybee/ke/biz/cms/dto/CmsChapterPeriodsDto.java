package cn.tinybee.ke.biz.cms.dto;

import cn.tinybee.ke.biz.cms.entity.CmsChapter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author hao.huang
 * @description
 * @date 2020/4/21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CmsChapterPeriodsDto extends CmsChapter {
    private List<CmsPeriodDto> children;
}
