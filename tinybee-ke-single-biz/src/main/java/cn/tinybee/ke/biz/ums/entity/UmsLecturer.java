package cn.tinybee.ke.biz.ums.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 讲师信息表
 *
 * 讲师分为集团内的  与外部申请的
 * </p>
 *
 * @author hao.huang
 * @since 2020-07-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_ums_lecturer")
public class UmsLecturer extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 讲师编号
     */
    private String lecturerNo;

    /**
     * 员工ID 内部员工有empno
     */
    private Long empId;

    /**
     * 讲师用户ID
     */
    private Long userId;

    /**
     * 讲师姓名
     */
    private String name;

    /**
     * 讲师昵称
     */
    private String nickname;

    /**
     * 讲师头像
     */
    private String avatar;

    /**
     * 职位
     */
    private String position;

    /**
     * 简介
     */
    private String intro;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 讲师类型1：机构内,2：自主申请
     */
    private Integer type;

    /**
     * 帐号启用状态:0->禁用；1->启用
     */
    private Boolean available;


}
