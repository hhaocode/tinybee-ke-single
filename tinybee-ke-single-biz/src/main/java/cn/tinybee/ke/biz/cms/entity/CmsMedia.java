package cn.tinybee.ke.biz.cms.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 视频信息
 * </p>
 *
 * @author hao.huang
 * @since 2020-06-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_media")
public class CmsMedia extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件UUID
     */
    private String mediaNo;

    /**
     * 文件扩展名
     */
    private String extension;

    /**
     * 文件中间部分路径
     */
    private String prefixDir;

    /**
     * 模块
     */
    private Integer module;

    /**
     * 类型 1 视频  2 音频
     */
    private Integer type;

    /**
     * 是否已转码
     */
    private Boolean transcoded;


}
