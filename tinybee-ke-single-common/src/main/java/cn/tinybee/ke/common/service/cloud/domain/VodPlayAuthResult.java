package cn.tinybee.ke.common.service.cloud.domain;

import cn.tinybee.ke.common.enums.VodType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/26 17:44
 */
@Data
public class VodPlayAuthResult implements Serializable {

    private static final long serialVersionUID = 2866131081973689953L;

    private String vid;

    private String requestId;

    private String playAuth;

    private String url;

    private String mediaType;

    private MetaInfo metaInfo = new MetaInfo();

    @Data
    public static class MetaInfo implements Serializable {
        private static final long serialVersionUID = -6494545606834604441L;
        private String coverUrl;
        private Float duration;
        private String status;
        private String title;
        private String videoId;
    }

}
