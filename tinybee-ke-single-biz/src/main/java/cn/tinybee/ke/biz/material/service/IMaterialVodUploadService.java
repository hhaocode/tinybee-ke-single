package cn.tinybee.ke.biz.material.service;

import cn.tinybee.ke.biz.material.entity.MaterialVodUpload;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.enums.VodType;
import cn.tinybee.ke.common.service.cloud.domain.VodPlayAuthResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 素材音视频表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-22
 */
public interface IMaterialVodUploadService extends IService<MaterialVodUpload> {

    /**
     * 上传
     * @param operator
     * @param file
     * @param type
     * @return
     */
    MaterialVodUpload upload(Operator operator, MultipartFile file, int type);

    VodPlayAuthResult.MetaInfo info(Long id);

}
