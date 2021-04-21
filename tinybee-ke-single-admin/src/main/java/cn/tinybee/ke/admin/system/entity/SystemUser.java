package cn.tinybee.ke.admin.system.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_system_user")
public class SystemUser extends BaseEntity<Long> {

    private static final long serialVersionUID = -1615296058202939165L;

    private String username;
    @JsonIgnore
    private String password;

    private Long empId;//员工ID

    private Boolean superAdmin;
    @JsonIgnore
    private Integer salt;// 盐值

    private Boolean available;

    private LocalDateTime lastLoginTime;
    private LocalDateTime lastChangePasswordTime;

    private Long creator;
    private LocalDateTime createTime;

}
