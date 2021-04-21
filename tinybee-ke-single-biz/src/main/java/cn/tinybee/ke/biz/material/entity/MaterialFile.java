package cn.tinybee.ke.biz.material.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 素材文件表
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_material_file")
public class MaterialFile extends BaseEntity<Long> {


    private static final long serialVersionUID = 1L;

    /**
     * 自动生成唯一文件名
     */
    private String fileUid;

    /**
     * 对象存储ID
     */
    private String ossId;

    /**
     * 文件唯一标识
     */
    private String md5;

    /**
     * 文件访问地址
     */
    private String fileUrl;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 分组ID
     */
    private Long groupId;

    /**
     * 分类ID
     */
    private Long classifyId;

    /**
     * 文件名
     */
    private String name;

    /**
     * 扩展名
     */
    private String extension;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String intro;

    /**
     * 大小
     */
    private Long size;

    /**
     * 1 图片  2 音频 3 视频
     */
    private Integer type;

    /**
     * 音视频上传
     */
    private Long vodUploadId;

    /**
     * 音视频点播ID
     */
    private String vodId;

    /**
     * 音视频封面
     */
    private String vodThumbUrl;

    /**
     * 音视频时长
     */
    private BigDecimal vodDuration;

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
    private Boolean deleted = false;

}
