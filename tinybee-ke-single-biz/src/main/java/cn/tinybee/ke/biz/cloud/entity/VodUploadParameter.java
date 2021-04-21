package cn.tinybee.ke.biz.cloud.entity;

import lombok.Data;

import java.util.List;

/**
 * @author hao.huang
 * @version v1.0.0
 * @Package : cn.tinybee.ke.ke.biz.cloud.entity
 * @Description : TODO
 * @Create on : 2020/7/6 10:05
 **/
@Data
public class VodUploadParameter {

    private String title;
    private String filename;
    private Long cateId;
    private String description;
    private List<String> tags;


}
