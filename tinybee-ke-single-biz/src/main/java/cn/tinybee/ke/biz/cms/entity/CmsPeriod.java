package cn.tinybee.ke.biz.cms.entity;

import cn.tinybee.ke.biz.material.dto.MaterialDto;
import cn.tinybee.ke.biz.material.entity.MaterialArticle;
import cn.tinybee.ke.biz.material.entity.MaterialFile;
import cn.tinybee.ke.biz.ums.entity.UmsStudentCourseLog;
import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 内容文章表
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_period")
public class CmsPeriod extends BaseEntity<Long> implements Comparable<CmsPeriod> {

    private static final long serialVersionUID = 1L;

    /**
     * 内容ID
     */
    @NotEmpty(message = "未知课程")
    private Long courseId;

    /**
     * 章节ID
     */
    private Long chapterId;

    /**
     * 标题
     */
    @NotBlank(message = "请输入标题")
    private String title;

    /**
     * 副标题
     */
    @NotBlank(message = "请输入副标题")
    private String subtitle;

    /**
     * 讲师ID  可以自定义讲师
     */
    private Long lecturerId;
    /**
     * 主题图片
     */
    private String coverUrl;

    /**
     * 背景颜色
     */
    private String bgColor;

    /**
     * 分享标题
     */
    private String shareTitle;

    /**
     * 顺序
     */
    private Long sort;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否免费
     */
    private Boolean free = false;

    /**
     * 试看时长  备用
     */
    private Integer freeDuration;

    /**
     * 摘要
     */
//    @Length(message = "")
    private String summary;

    /**
     * 1 待更新  2 已更新 3 下架
     */
    private Integer status;

    // 是否直播  开始时间 结束时间

    /**
     * 资源类型  资源类型  3 视频  5 图文+音频6直播
     */
    private Integer resourceType;

    /**
     *
     */
    @TableField(exist = false)
    private MaterialDto resource;

    /**
     * 资源ID 视频  文章 直播
     */
    private Long resourceId;

    /**
     * 附加资源ID 当为文章时 可选择音频ID
     */
    private Long additionResourceId;

    @TableField(exist = false)
    private MaterialDto additionResource;

    /**
     * 文件ID
     */
    @TableField(exist = false)
    private List<Long> fileIds;

    /**
     * 文件列表
     */
    @TableField(exist = false)
    private List<MaterialFile> files;

    @TableField(exist = false)
    private List<CmsPeriod> children;

    @TableField(exist = false)
    private CmsPeriodType type;

    /**
     * 已学习时长
     */
    @TableField(exist = false)
    private BigDecimal studiedDuration;

    @TableField(exist = false)
    private BigDecimal studyProgress;

    @TableField(exist = false)
    private UmsStudentCourseLog log;

    private Boolean hasLive = false; // false

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(exist = false)
    private LocalDateTime liveStartTime;

    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime liveEndTime;


    @Override
    public int compareTo(CmsPeriod o) {
        if (o == null) {
            return 1;
        }
        if (this.sort == null) {
            return 1;
        }
        if (o.getSort() == null) {
            return 1;
        }
        if (this.sort.equals(o.getSort())) {
            return 1;
        }
        return (int) (this.sort - o.getSort());
    }

    public static enum CmsPeriodType {
        CHAPTER, PERIOD
    }

}
