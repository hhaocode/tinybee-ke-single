package cn.tinybee.ke.biz.pm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_pm_configuration")
public class PmConfiguration extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 配置代码 唯一
     */
    private String configType;

    /**
     * 配置名称
     */
    private String configName;

    /**
     * 默认值
     */
    @NotBlank(message = "默认不能为空")
    private String defaultValue;

    /**
     * 当前值
     */
    private String currentValue;

    /**
     * 执行器的Bean名称
     */
    private String handlerName;

    /**
     * 修改人
     */
    private Long modifier;

    /**
     * 修改该时间
     */
    private LocalDateTime modifyTime;

    /**
     * 最近一次执行时间
     */
    private LocalDateTime executeTime;


    @TableField(exist = false)
    private boolean immediatelyTriggered = false;


}
