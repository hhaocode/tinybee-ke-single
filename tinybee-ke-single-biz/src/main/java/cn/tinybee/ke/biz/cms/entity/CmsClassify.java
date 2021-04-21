package cn.tinybee.ke.biz.cms.entity;

import cn.tinybee.ke.common.handle.tree.ITreeNode;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 内容分类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_classify")
public class CmsClassify extends BaseEntity<Long> implements ITreeNode {

    private static final long serialVersionUID = 1L;

    public static final int TYPE_DIRECTION = 1; //方向
    public static final int TYPE_CLASSIFY = 2; // 分类

    public static final Map<Integer, String> TYPE_MESSAGE = new HashMap(2);
    static {
        TYPE_MESSAGE.put(TYPE_DIRECTION, "方向");
        TYPE_MESSAGE.put(TYPE_CLASSIFY, "分类");
    }

    /**
     * 上级ID
     */
    private Long pid;

    /**
     * 名称
     */
    @NotBlank(message = "请输入名称")
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 图片
     */
    private String imgUrl;

    /**
     * 类型 1 方向 2 分类
     */
    @NotEmpty(message = "类型不能为空")
    private Integer type;

    /**
     * 排序
     */
    @NotEmpty(message = "请输入顺序")
    private Long sort;

    /**
     * 可用状态
     */
    private Boolean available;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除
     */
    private Boolean deleted;


    @Override
    public String getLabel() {
        return name;
    }

    @TableField(exist = false)
    private List<CmsClassify> children;

    @TableField(exist = false)
    private List<CmsCourse> courses;

}
