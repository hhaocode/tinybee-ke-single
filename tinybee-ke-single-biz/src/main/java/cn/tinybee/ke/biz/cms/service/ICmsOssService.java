package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsOss;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 素材文件表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-21
 */
public interface ICmsOssService extends IService<CmsOss> {

    List<CmsOss> upload(Operator operator, MultipartFile[] files, Integer type, Integer purpose);

    List<CmsOss> listByType(Operator operator, Integer type, Integer purpose);

    Page<CmsOss> page (Operator user, Page page, Integer type, Integer purpose);
}
