package cn.tinybee.ke.common.handle.tree;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname AbstractTreeNo
 * @Description TODO
 * @Date 2020/5/28 14:00
 * @Created by hao.huang
 */
public interface ITreeNode {

    Long getId();

    Long getPid();

    String getLabel();

}
