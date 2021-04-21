package cn.tinybee.ke.common.service.cloud.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/26 9:51
 */
@Data
public class VodUploadResult implements Serializable {

    private String vid;
    private String channel;

    private Long size;
    private BigDecimal duration;

    private String coverURL;

}
