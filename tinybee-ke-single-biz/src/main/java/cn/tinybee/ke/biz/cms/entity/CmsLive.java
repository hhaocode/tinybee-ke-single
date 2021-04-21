package cn.tinybee.ke.biz.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_live")
public class CmsLive extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final int WAIT_LIVE_STATUS = 1;
    public static final int LIVING_STATUS = 2;
    public static  final int LIVED_STATUS = 3;


    public static final int LIVE_TYPE_COURSE = 1;
    public static final int LIVE_TYPE_ADV = 2;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 1 课程内 2 广告
     */
    private Integer type;

    /**
     * 封面
     */
    private String coverUrl;

    /**
     * 房间ID
     */
    private Long roomId;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 是否已转码
     */
    private Boolean hasTranscoding;

    /**
     * 转码成功后的视频Id
     */
    private Long vodId;

    /**
     * 课时Id
     */
    private Long periodId;

    /**
     * 是否已结束
     */
    private Integer status = WAIT_LIVE_STATUS;

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
