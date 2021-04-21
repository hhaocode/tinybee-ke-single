package cn.tinybee.ke.biz.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 素材文件表
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_oss")
public class CmsOss extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    private String name;

    /**
     * 对象存储ID
     */
    private String eTag;

    /**
     * 文件唯一标识
     */
    private String md5;

    /**
     * 自动生成唯一文件名
     */
    private String fileUid;

    /**
     * 存储渠道(ALIYUN,TENCENT,QINIU)
     */
    private String channel;

    /**
     * 类型 类型  1图片 2压缩文件
     */
    private Integer type;

    /**
     * 1 封面 2 广告 3 视频贴片 4 头像
     */
    private Integer purpose;

    /**
     * 扩展名
     */
    private String extension;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 访问地址
     */
    private String url;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 上传人员
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人员
     */
    private Long modifier;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

    /**
     * 是否删除
     */
    private Boolean deleted;


}
