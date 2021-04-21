package cn.tinybee.ke.biz.material.entity;

import cn.tinybee.ke.common.enums.VodType;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 素材音视频表
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_material_vod_upload")
public class MaterialVodUpload extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 文件唯一标识
     */
    private String md5;

    /**
     * 文件名
     */
    private String name;

    /**
     * 渠道视频ID
     */
    private String vid;

    /**
     * 存储渠道(ALIYUN,TENCENT,QINIU)
     */
    private String channel;

    /**
     * 2 音频  3 视频
     */
    private Integer type;

    private Long size;

    /**
     * 扩展名
     */
    private String extension;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 上传人员
     */
    private Long creator;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 修改人员
     */
    private Long modifier;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyTime;

    /**
     * 是否删除
     */
    private Boolean deleted;


}
