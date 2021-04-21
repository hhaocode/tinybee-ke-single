package cn.tinybee.ke.admin.system.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author hao.huang
 * @version v1.0.0
 * @Package : cn.tinybee.ke.admin.system.dto
 * @Description : TODO
 * @Create on : 2020/7/13 14:09
 **/
@Data
public class SystemLecturerDto {

    @NotEmpty(message = "简介不能为空")
    private String intro;

    @NotNull(message = "用户不能为空")
    private Long userId;

}
