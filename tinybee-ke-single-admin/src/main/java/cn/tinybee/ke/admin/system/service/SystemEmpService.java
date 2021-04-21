package cn.tinybee.ke.admin.system.service;

import cn.tinybee.ke.admin.system.dto.SystemLecturerDto;
import cn.tinybee.ke.admin.system.entity.SystemEmp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
public interface SystemEmpService extends IService<SystemEmp> {

    boolean isExist(String jobNo);

    boolean deleteEmp(Long id);

    boolean turnLecturer(Long empId, SystemLecturerDto lecturerDto, Long userId);
}
