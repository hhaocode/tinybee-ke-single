package cn.tinybee.ke.biz.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 图片
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_image")
public class CmsImage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * md5码
     */
    private String md5;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 文件类型
     */
    private String contentType;

    /**
     * 扩展名
     */
    private String extension;


}
