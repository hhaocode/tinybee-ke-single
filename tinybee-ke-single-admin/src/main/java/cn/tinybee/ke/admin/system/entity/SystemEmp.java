package cn.tinybee.ke.admin.system.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_emp")
public class SystemEmp extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 工号
     */
    @NotBlank(message = "工号不能为空")
    private String jobNo;

    /**
     * 姓名
     */
    @NotBlank(message = "工号不能为空")
    private String name;

    /**
     * 性别M F U
     */
    private String gender;

    /**
     * 婚否
     */
    private Integer married;

    /**
     * 学历：1小学 2 初中 3 高中 4 大专,5 本科,6 研究生,7 博士,8 博士后 9 其他
     */
    private Integer education;

    /**
     * 手机
     */
    @NotBlank(message = "工号不能为空")
    private String mobile;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 住址
     */
    @NotBlank(message = "住址不能为空")
    private String address;

    /**
     * 职务ID
     */
    private Long postId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 上司ID
     */
    private Long mgrId;

    /**
     * 入职日期
     */
    @NotBlank(message = "入职日期不能为空")
    private LocalDate hiredate;

    /**
     * 离职日期
     */
    private LocalDate termdate;

    /**
     * 状态：1在职,2休假,3离职,4退休
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 逻辑删除
     */
    private Boolean deleted;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
