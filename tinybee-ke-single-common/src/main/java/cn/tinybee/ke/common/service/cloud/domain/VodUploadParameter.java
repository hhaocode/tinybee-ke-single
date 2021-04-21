package cn.tinybee.ke.common.service.cloud.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/26 9:54
 */
@Data
public class VodUploadParameter implements Serializable {

    private String title;
    private String filename;
    private Long cateId;
    private String description;
    private List<String> tags;

}
