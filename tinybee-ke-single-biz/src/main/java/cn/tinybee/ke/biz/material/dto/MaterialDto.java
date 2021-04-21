package cn.tinybee.ke.biz.material.dto;

import cn.tinybee.ke.biz.cms.entity.CmsLive;
import cn.tinybee.ke.biz.material.entity.MaterialArticle;
import cn.tinybee.ke.biz.material.entity.MaterialFile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/22 11:35
 */
@Data
public class MaterialDto {

    private Long id;

    /**
     * 1 图片  2 音频 3 视频 4 文件 5 文章  6 直播
     */
    private Integer type;

    private BigDecimal duration;

    private MaterialFile file;

    private MaterialArticle article;

    private CmsLive live;

    private Long size;

    private String title;

    private String url;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime endTime;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

    public static enum MaterialType {
        file, image, video, audio, article
    }


    public static MaterialDto fromMaterialFile (MaterialFile file) {
        MaterialDto res = new MaterialDto();
        res.setId(file.getId());
        res.setDuration(file.getVodDuration());
        res.setFile(file);
        res.setUrl(file.getFileUrl());
        res.setTitle(StringUtils.isNotBlank(file.getTitle()) ? file.getTitle() : file.getName());
        res.setSize(file.getSize());
        res.setCreateTime(file.getCreateTime());
        return res;
    }

    public static MaterialDto fromMaterialArticle (MaterialArticle article) {
        MaterialDto res = new MaterialDto();
        res.setId(article.getId());
        res.setCreateTime(article.getCreateTime());
        res.setTitle(article.getTitle());
        res.setUrl(article.getCoverUrl());
        return res;
    }

    public static MaterialDto formCmsLive (CmsLive live) {
        MaterialDto res = new MaterialDto();
        res.setId(live.getId());
        res.setTitle(live.getTitle());
        res.setUrl(live.getCoverUrl());
        res.setLive(live);
        res.setCreateTime(live.getCreateTime());
        res.setStartTime(live.getStartTime());
        res.setEndTime(live.getEndTime());
        return res;
    }
}
