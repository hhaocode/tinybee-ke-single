package cn.tinybee.ke.common.enums;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/15 14:42
 */
public enum  CourseType {
    GENERAL("general", "课程"),
    COLUMN("general", "专栏"),
    PUBLIC("public", "公开课");


    private final String key;
    private final String label;


    CourseType(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getKey() {
        return key;
    }
}
