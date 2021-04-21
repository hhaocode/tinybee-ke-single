package cn.tinybee.ke.biz.cms.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import cn.tinybee.ke.common.enums.FileTypeEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * oss上文件
 * </p>
 *
 * @author hao.huang
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_file")
public class CmsFile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * md值
     */
    private String md5;

    /**
     * sha1值
     */
    private String sha1;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * oss上文件信息
     */
    private String fileId;

    /**
     * 路径
     */
    private String url;

    /**
     * 后缀
     */
    private String suffix;

    private String contentType;

    /**
     * 文件类型(1:附件;2;图片;3:视频)
     */
    private FileTypeEnum type;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 状态
     */
    private Boolean available;

    /**
     * 音视频时长
     */
    private String vodLength;

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
