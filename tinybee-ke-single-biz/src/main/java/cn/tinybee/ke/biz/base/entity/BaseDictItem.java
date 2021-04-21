package cn.tinybee.ke.biz.base.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 字典子项表
 * </p>
 *
 * @author hao.huang
 * @since 2020-08-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_base_dict_item")
public class BaseDictItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    private Long dictId;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典值
     */
    private String value;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否默认
     */
    private Boolean isDefault;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态 true 可用 false 不可用
     */
    private Boolean available;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 更新人
     */
    private Long modifier;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


}
