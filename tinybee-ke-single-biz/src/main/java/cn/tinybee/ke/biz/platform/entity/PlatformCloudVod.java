package cn.tinybee.ke.biz.platform.entity;

import cn.tinybee.ke.biz.configuration.enums.CloudType;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 云点播
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_platform_cloud_vod")
public class PlatformCloudVod extends BaseEntity<CloudType> {

    private static final long serialVersionUID = 1L;

    private String name;

    /**
     * 点播服务接入区域
     */
    private String regionId;

    private String workflowId;

    private Boolean isDefault;

}
