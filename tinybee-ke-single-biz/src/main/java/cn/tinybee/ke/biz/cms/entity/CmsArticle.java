package cn.tinybee.ke.biz.cms.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("t_cms_article")
public class CmsArticle extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 内容ID
     */
    private Integer contentId;

    /**
     * 章节ID
     */
    private Integer chapterId;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 主题图片
     */
    private String cover;

    /**
     * 背景颜色
     */
    private String bgcolor;

    /**
     * 分享标题
     */
    private String shareTitle;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
