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
@TableName("t_platform_sms_config")
public class PlatformSmsConfig extends BaseEntity<CloudType> {

    private static final long serialVersionUID = 1L;

    private String purpose;

    private String templateCode;

    private String defaultCloud;

    /**
     * 阿里云模板代码
     */
    private String aliyunTemplateCode;

    /**
     * 腾讯云模板代码
     */
    private String tencentTemplateCode;


}
