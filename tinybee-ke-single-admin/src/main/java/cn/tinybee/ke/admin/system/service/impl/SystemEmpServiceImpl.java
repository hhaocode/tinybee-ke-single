package cn.tinybee.ke.admin.system.service.impl;

import cn.tinybee.ke.admin.system.dto.SystemLecturerDto;
import cn.tinybee.ke.admin.system.entity.SystemEmp;
import cn.tinybee.ke.admin.system.entity.SystemUser;
import cn.tinybee.ke.admin.system.mapper.SystemEmpMapper;
import cn.tinybee.ke.admin.system.service.SystemEmpService;
import cn.tinybee.ke.admin.system.service.SystemUserService;
import cn.tinybee.ke.biz.ums.entity.UmsLecturer;
import cn.tinybee.ke.biz.ums.service.IUmsLecturerService;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.util.AssertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 员工表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
@Service
public class SystemEmpServiceImpl extends ServiceImpl<SystemEmpMapper, SystemEmp> implements SystemEmpService {

    @Autowired
    @Lazy
    private SystemUserService systemUserService;

    @Autowired
    private IUmsLecturerService iUmsLecturerService;

    @Override
    public boolean isExist(String jobNo) {
        QueryWrapper<SystemEmp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("job_no", jobNo);
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean deleteEmp(Long id) {
        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_id", id);
        systemUserService.remove(queryWrapper);
        UpdateWrapper<SystemEmp> systemEmpUpdateWrapper = new UpdateWrapper<>();
        systemEmpUpdateWrapper.eq("id", id).set("deleted", true);
        return this.update(systemEmpUpdateWrapper);
    }

    @Transactional
    @Override
    public boolean turnLecturer(Long empId, SystemLecturerDto lecturerDto, Long userId) {
        UmsLecturer dbLecturer = iUmsLecturerService.getByEmpId(empId);
        if (dbLecturer != null) {
            throw new BusinessException("该员工已成为讲师");
        }

        SystemEmp emp = this.getById(empId);
        AssertUtils.notNull(emp, "员工不存在");
        AssertUtils.isFalse(emp.getDeleted(), "员工已被删除");
        if (emp.getStatus() != 1) { //&& emp.getStatus() != 2) {
            throw new BusinessException("该员工不是在职状态");
        }
        //
        UmsLecturer lecturer = new UmsLecturer();
        lecturer.setAvailable(true);
        lecturer.setEmail(emp.getEmail());
        lecturer.setEmpId(empId);
        lecturer.setIntro(lecturerDto.getIntro());
//        lecturer.setLecturerNo()
        lecturer.setMobile(emp.getMobile());
        lecturer.setName(emp.getName());
//        lecturer.setPosition()
        lecturer.setType(1);
        // 选择已有用户
//        lecturer.setUserId(lecturerDto.getUserId());
        return iUmsLecturerService.save(lecturer);
    }
}
