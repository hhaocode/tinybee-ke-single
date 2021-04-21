package cn.tinybee.ke.biz.cms.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 内容素材表
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_material")
public class CmsMaterial extends BaseEntity {

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
     * 类型 1 普通文件 2 图片 3 视频 4 音频
     */
    private Integer type;

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
     * 渠道视频ID
     */
    private String vid;

    /**
     * 封面地址
     */
    private String coverUrl;

    /**
     * 音视频长度
     */
    private String vLength;

    /**
     * 时长。单位：秒。
     */
    private BigDecimal duration;

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
