package cn.tinybee.ke.biz.material.service;

import cn.tinybee.ke.biz.material.entity.MaterialFile;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.service.cloud.domain.VodPlayAuthResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-23
 */
public interface IMaterialFileService extends IService<MaterialFile> {

    List<MaterialFile> upload(MultipartFile[] files, int type, Long currentUserId);

    Map search(Page page, Long groupId, String name, int type, Long currentUserId);

    Page<MaterialFile> page(Page page, int type, Long groupId, String name);

    Boolean deleteBatch(Operator operator, List<Long> ids);

    MaterialFile saveOrModifyVod(MaterialFile param, Long currentUserId);

    Page<MaterialFile> pageQuery(Page page, Integer type, String title, List<Long> groupIds);

    /**
     *
     * @param id
     * @return
     */
    VodPlayAuthResult getVodPlayAuth(Long id, boolean auth);

    boolean deleteById(Operator operator, Long id);
}
