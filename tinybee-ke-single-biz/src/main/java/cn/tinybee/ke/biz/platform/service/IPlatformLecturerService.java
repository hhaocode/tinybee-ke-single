package cn.tinybee.ke.biz.platform.service;

import cn.tinybee.ke.biz.platform.entity.PlatformLecturer;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师信息表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-28
 */
public interface IPlatformLecturerService extends IService<PlatformLecturer> {

    /**
     * 保存
     * @param user
     * @param param
     * @return
     */
    boolean saveOrModify(Operator user, PlatformLecturer param);

    /**
     * 删除
     * @param user
     * @param id
     * @return
     */
    boolean deleteById (Operator user, Long id);


    PlatformLecturer getDetailById(Long id);
}
