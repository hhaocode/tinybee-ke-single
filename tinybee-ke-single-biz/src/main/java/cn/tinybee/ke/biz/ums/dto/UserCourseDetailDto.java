package cn.tinybee.ke.biz.ums.dto;

import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import lombok.Data;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/11/6 15:11
 */
@Data
public class UserCourseDetailDto extends CmsCourse {

    public Long userCourseId;

    public String process;

}
