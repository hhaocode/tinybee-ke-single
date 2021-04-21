package cn.tinybee.ke.biz.material.entity;

import cn.tinybee.ke.biz.material.dto.MaterialDto;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_material_group")
public class MaterialGroup extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @NotBlank(message = "请输入分组名称")
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 类型 4(文件) 1(图片) 3(视频) 2(视频)
     */
    @NotNull(message = "请选择分组类型")
    private Integer type;

    /**
     * 排序
     */
    private Long sort = 0L;

    /**
     * 上传人员
     */
    private Long creator;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 修改人员
     */
    private Long modifier;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyTime;


}
