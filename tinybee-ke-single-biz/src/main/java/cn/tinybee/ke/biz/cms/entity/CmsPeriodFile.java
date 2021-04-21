package cn.tinybee.ke.biz.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao.huang
 * @since 2021-04-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_period_file")
public class CmsPeriodFile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long periodId;

    private Long fileId;


}
