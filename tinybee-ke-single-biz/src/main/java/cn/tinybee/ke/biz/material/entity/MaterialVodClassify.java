package cn.tinybee.ke.biz.material.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import cn.tinybee.ke.common.handle.tree.ITreeNode;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_material_vod_classify")
public class MaterialVodClassify extends BaseEntity<Long> implements ITreeNode {

    private static final long serialVersionUID = 1L;

    /**
     * 上级ID 0最高级
     */
    private Long pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 封面ID
     */
    private Long coverId;

    /**
     * 类型 video  audio
     */
    private String type;

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


    @Override
    public String getLabel() {
        return this.name;
    }
}
