package cn.tinybee.ke.common.service.cloud.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/23 14:16
 */
@Data
public class OSSUploadResult implements Serializable {

    private static final long serialVersionUID = 2650865775570037257L;
    private String fileUid;
    private String url;
    private String eTag;
    private String channel;
    private String extension;
    private Long size;
    private String filePath;
}
