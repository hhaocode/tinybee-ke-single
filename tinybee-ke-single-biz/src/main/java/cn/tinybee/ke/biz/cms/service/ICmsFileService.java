package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsFile;
import cn.tinybee.ke.biz.cms.entity.CmsImage;
import cn.tinybee.ke.biz.cms.entity.CmsVod;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.enums.FileTypeEnum;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * oss上文件 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-09-29
 */
public interface ICmsFileService extends IService<CmsFile> {

    CmsFile upload(MultipartFile[] files, FileTypeEnum type);


    CmsVod uploadVod (MultipartFile file);

    List<CmsImage> uploadImage(MultipartFile[] files) throws IOException;

    Page<CmsFile> imagePage(PageParam pageParam);
}

