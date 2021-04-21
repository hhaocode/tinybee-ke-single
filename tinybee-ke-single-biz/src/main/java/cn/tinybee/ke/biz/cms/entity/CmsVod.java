package cn.tinybee.ke.biz.cms.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import cn.tinybee.ke.common.enums.TranscodingState;
import cn.tinybee.ke.common.enums.VodType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 视频点播
 * </p>
 *
 * @author hao.huang
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_vod")
public class CmsVod extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 上传请求ID
     */
    @TableField("requestId")
    private String requestId;


    private String filename;


    private String title;


    private String intro;

    private VodType vodType;

    /**
     * 音视频ID
     */
    private String vid;

    /**
     * 上传地址ID MediaUrl
     */
    private String uploadAddress;

    /**
     * 音视频ID
     */
    private String uploadAuth;

    /**
     * 封面
     */
    private String coverUrl;

    /**
     * 标签
     */
    private String tag;

    /**
     * 渠道 aliyun tencent
     */
    private String channel;

    /**
     * 状态
     */
    private Integer status;

    private TranscodingState transcodingState; // 待转码  正在转码  已转码

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
