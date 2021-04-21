package cn.tinybee.ke.portal.api.dto;

import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/1/16 10:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectCourse {

    private String name;
    private String code;
    private List<CmsCourse> courses;
}
