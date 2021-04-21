package cn.tinybee.ke.common.enums;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/16 14:18
 */
public enum VodType {
    video("视频"),
    audio("音频");

    private String text;

    VodType(String text) {
        this.text = text;
    }
}
