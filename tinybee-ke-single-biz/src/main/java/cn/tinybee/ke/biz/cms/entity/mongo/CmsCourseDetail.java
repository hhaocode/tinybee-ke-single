package cn.tinybee.ke.biz.cms.entity.mongo;

import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.common.base.MongoBaseDomain;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/11/17 16:46
 */
@Data
@Document(CmsCourseDetail.COLLECTION_NAME)
public class CmsCourseDetail extends MongoBaseDomain {

    protected static final String COLLECTION_NAME = "CmsCourseDetail";

    private CmsCourse data;

    private String description;

}
