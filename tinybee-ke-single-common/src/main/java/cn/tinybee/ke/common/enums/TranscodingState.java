package cn.tinybee.ke.common.enums;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/21 12:51
 */
public enum TranscodingState {

    waiting("待转码"),
    doing("正在转码"),
    done("已转码");

    private String label;

    TranscodingState (String label) {
        this.label = label;
    }

}
