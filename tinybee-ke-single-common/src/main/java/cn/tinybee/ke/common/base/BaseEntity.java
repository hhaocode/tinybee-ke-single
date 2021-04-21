package cn.tinybee.ke.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/27
 */
@Data
public class BaseEntity<ID> implements Serializable {
    private static final long serialVersionUID = 5088367113042786482L;

    @TableId(type = IdType.AUTO)
    protected ID id;

//    private Boolean deleted = false;
//
//    private LocalDateTime createTime;
//
//    private ID creator;
//
//    private ID modifier;
//
//    private LocalDateTime modifyTime;


//    public void setCreateInfo (ID userId) {
//        this.deleted = false;
//        LocalDateTime now = LocalDateTime.now();
//        this.createTime = now;
//        this.modifyTime = now;
//        this.creator = userId;
//        this.modifier = userId;
//    }
//
//    public void setModifyInfo (ID userId) {
//        LocalDateTime now = LocalDateTime.now();
//        this.creator = userId;
//        this.modifyTime = now;
//    }
//
//    public void delete (ID userId) {
//        this.deleted = true;
//        setModifyInfo(userId);
//    }

}
