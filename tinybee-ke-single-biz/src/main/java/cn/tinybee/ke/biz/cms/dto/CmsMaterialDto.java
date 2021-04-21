package cn.tinybee.ke.biz.cms.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/22 10:20
 */
@Data
public class CmsMaterialDto {

    private Long id;

    private String title;

    private String imgUrl; //

    private Long vodId;

    private String vLength;

    private LocalDateTime createTime;



    public static enum MaterialType {
        image, video, audio
    }
}
