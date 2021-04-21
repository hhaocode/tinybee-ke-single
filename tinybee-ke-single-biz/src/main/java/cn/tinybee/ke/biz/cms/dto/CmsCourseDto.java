package cn.tinybee.ke.biz.cms.dto;

import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.biz.ums.entity.UmsStudentCourse;
import lombok.Data;

/**
 * @author hao.huang
 * @description
 * @date 2020/4/15
 */
@Data
public class CmsCourseDto extends CmsCourse {
    private static final long serialVersionUID = 4625570340450436010L;

//    private List<Long> categorys;

    private String categoryName;

    private UmsStudentCourse umsStudentCourse;

}
