package cn.tinybee.ke.biz.platform.entity;

import cn.tinybee.ke.biz.configuration.enums.CloudType;
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
 * @since 2021-03-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_platform_cloud_oss")
public class PlatformCloudOss extends BaseEntity<CloudType> {

    private static final long serialVersionUID = 1L;

    private String name;

    private String endpoint;

    private String bucketName;

    private Boolean isDefault;

}
