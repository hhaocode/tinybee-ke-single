package cn.tinybee.ke.biz.sem.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 广告信息
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sem_advertisement")
public class SemAdvertisement extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 广告标题
     */
    private String title;

    /**
     * 广告图片
     */
    private String coverUrl;

    /**
     * 广告链接
     */
    private String targetUrl;

    /**
     * 目标
     */
    private String target;

    /**
     * 广告位置(1首页轮播)
     */
    private Integer location;

    /**
     * 开始时间
     */
    private LocalDateTime beginTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 平台
     */
    private Integer platform;

    /**
     * 状态
     */
    private Boolean available;

    /**
     * 排序
     */
    private Integer sort;

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


}
