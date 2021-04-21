package cn.tinybee.ke.biz.sem.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sem_sku")
public class SemSku extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    private Long spuId;

    /**
     * 类别
     */
    private Integer type;

    /**
     * 引用ID
     */
    private Long referenceId;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String images;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 参数
     */
    private String param;

    /**
     * 是否上架
     */
    private Boolean saleable;

    /**
     * 是否有效
     */
    private Boolean valid;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 是否删除
     */
    private Boolean deleted;


}
