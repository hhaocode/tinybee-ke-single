package cn.tinybee.ke.biz.material.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_material_article")
public class MaterialArticle extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 组ID
     */
    private Integer groupId;

    /**
     * 标题
     */
    @NotBlank(message = "请输入标题")
    private String title;

    /**
     * 类型 0 文章 1 手记
     */
    private Integer type = 0;
    /**
     * 副标题
     */
    @NotBlank(message = "请输入副标题")
    private String subtitle;

    /**
     * 总结
     */
    @NotBlank(message = "请输入总结")
    private String summary;

    /**
     * 封面
     */
    @NotBlank(message = "请选择封面")
    private String coverUrl;

    /**
     * 内容
     */
    @NotBlank(message = "请输入内容")
    private String content;

    /**
     * 免费内容
     */
    private String freeContent;

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
