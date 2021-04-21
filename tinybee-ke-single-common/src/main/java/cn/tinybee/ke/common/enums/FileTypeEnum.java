package cn.tinybee.ke.common.enums;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/9/29 19:16
 */
public enum FileTypeEnum {
    audio("音频"),
    video("视频"),
    image("图片"),
    xml("xml"),
    html("html"),
    css("css"),
    pdf("pdf"),
    js("javascript"),
    doc(""),
    docx("");



    String label;
    FileTypeEnum(String label) {
        this.label  =label;
    }

    public String getLabel() {
        return label;
    }
}
