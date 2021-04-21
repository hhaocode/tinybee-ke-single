package cn.tinybee.ke.biz.cms.mongo;

import cn.tinybee.ke.biz.cms.entity.mongo.CmsCourseDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/11/17 16:52
 */
public interface CmsCourseDetailRepository extends MongoRepository<CmsCourseDetail, Long> {


}
