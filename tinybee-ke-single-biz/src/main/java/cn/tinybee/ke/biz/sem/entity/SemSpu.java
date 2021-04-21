package cn.tinybee.ke.biz.sem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sem_spu")
public class SemSpu extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * spu类型 1 课程  2 每日一课会员
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
     * 子标题
     */
    private String subtitle;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 规格类型 备用
     */
    private String specType;

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
    private Boolean deleted = false;



}
