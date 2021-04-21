package cn.tinybee.ke.biz.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 讲师信息表
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_platform_lecturer")
public class PlatformLecturer extends BaseEntity<Long> {

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
    @NotBlank(message = "请输入讲师姓名")
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
    @NotBlank(message = "请输入简介")
    private String intro;

    /**
     * 手机号码
     */
    @NotBlank(message = "请输入手机号码")
    private String mobile;

    /**
     * 邮箱
     */
    @NotBlank(message = "请输入邮箱")
    private String email;

    /**
     * 讲师类型1：机构内,2：自主申请  默认是机构类 2 以后扩展
     */
    private Integer type = 1;

    /**
     * 是否有效
     */
    private Boolean available = true;

    /**
     * 是否删除
     */
    private Boolean deleted = false;
}
