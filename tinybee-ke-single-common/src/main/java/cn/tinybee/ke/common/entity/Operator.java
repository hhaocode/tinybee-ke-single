package cn.tinybee.ke.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 操作用户
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/9 10:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operator implements Serializable {

    private static final long serialVersionUID = 340441559669226420L;
    private Long id;
    private String username;
    private String token;
    private Long deptId;
    private boolean superAdmin;
}
