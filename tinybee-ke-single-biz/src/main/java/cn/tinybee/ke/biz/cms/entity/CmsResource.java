package cn.tinybee.ke.biz.cms.entity;

import java.math.BigDecimal;
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
 * @since 2020-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_resource")
public class CmsResource extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @NotBlank(message = "请输入标题")
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 是否已转码
     */
    private Integer transcoded = 1;

    /**
     * 单价
     */
    private BigDecimal piecePrice = BigDecimal.ZERO;

    /**
     * 划线价
     */
    private BigDecimal linePrice = BigDecimal.ZERO;

    /**
     * 是否开放
     */
    private String isPublic;

    /**
     * 图片地址
     */
    @NotBlank(message = "封面图片不能为空")
    private String imgUrl;

    /**
     * 压缩图片地址
     */
    private String imgUrlCompressed;

    /**
     * 类型 1 图文  2 音频3 视频  （课程资源）4 直播 5 电子书 6 打包文件
     */
    @NotBlank(message = "资源类型不能为空")
    private Integer type;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态(0 待上架 1已上架 2 ) 
     */
    private Integer resourceStatus;

    /**
     * 视频或者音频id
     */
    private Long vid;

    /**
     * 内容
     */
    private String content;

    /**
     * 免费内容
     */
    private String freeContent;

    /**
     * 对象存储地址
     */
    private Long ossId;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private Long modifier;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime liveStartTime;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime liveEndTime;

}
