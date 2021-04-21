package cn.tinybee.ke.biz.cloud.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/9/8 9:52
 */
@Data
public class OSSUploadResult implements Serializable {

    private String id;
    private String name;
    private String extension;
    private String uri;
}
