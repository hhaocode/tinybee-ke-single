package cn.tinybee.ke.biz.cms.dto;

import cn.tinybee.ke.common.entity.KeywordQueryParam;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/28 17:48
 */
@Data
public class CourseSearchParamDto extends KeywordQueryParam {
    private static final long serialVersionUID = 1857021143964763329L;

    private Long cateId;

    private Long directionId;

    private Long classifyId;

    private Boolean free;

    private Integer type;

    private List<Integer> types;

    private Integer status;
}
