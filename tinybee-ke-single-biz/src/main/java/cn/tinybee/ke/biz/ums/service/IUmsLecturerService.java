package cn.tinybee.ke.biz.ums.service;

import cn.tinybee.ke.biz.ums.entity.UmsLecturer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师信息表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-07-13
 */
public interface IUmsLecturerService extends IService<UmsLecturer> {


    boolean turnLecturer(Long empId, Long userId);

    UmsLecturer getByEmpId(Long empId);

}
