package cn.tinybee.ke.biz.cms.entity;

import java.math.BigDecimal;

import cn.tinybee.ke.biz.platform.entity.PlatformLecturer;
import cn.tinybee.ke.common.base.BaseEntity;
import cn.tinybee.ke.common.entity.IdNameType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 内容主表
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_course")
public class CmsCourse extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final int WAITING_ON_SHELF = 1;
    public static final int ON_SHELF = 2;
    public static final int OFF_SHELF = 3;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 副标题
     */
    @NotBlank(message = "副标题不能为空")
    private String subtitle;

    /**
     * 讲师ID
     */
    private Long lecturerId;

    /**
     * 1课程（视频、直播、附件、说明） 2专栏（文章、音频、附件、说明） 3 每日一课（短视频） 4 公开课（仅视频或者与课程一样, 直播， 附件, 简介） 5 微课  6 训练营
     */
    private Integer type;

    /**
     * 状态 1 待上架 2 已上架 3 已下架
     */
    private Integer status = 1;


    /**
     * 广告图片
     */
    private String coverUrl;

    /**
     * 背景颜色
     */
    private String bgColor;

    /**
     * 是否免费
     */
    @NotNull(message = "请选择是否免费")
    private Boolean free;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 总课时
     */
    private BigDecimal totalHours;

    /**
     * 总周期
     */
    private BigDecimal totalPeriod;

    /**
     * 报名人数
     */
    private Integer applyNum;


    /**
     * 1 直接上架(不能修改) 2 定时上架（可修改） 3 手动上架
     */
    private Integer onShelfType;

    /**
     * 上架时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime onShelfTime;

    /**
     * 实际上架时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime  actualOnShelfTime;


    /**
     * 创建人
     */
    private Long creator;

    /**
     * VIP是否免费
     */
    private Boolean vipFree;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    private Long modifier;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyTime;

    @TableField(exist = false)
    private List<Long> directionIds;

    @TableField(exist = false)
    private List<CmsClassify> directions;

    @TableField(exist = false)
    private List<Long> classifyIds;

    @TableField(exist = false)
    private List<CmsClassify> classifies;

    @TableField(exist = false)
    private String description;

    @TableField(exist = false)
    private Boolean purchased;

    @TableField(exist = false)
    private PlatformLecturer lecturer;

}
