package cn.tinybee.ke.biz.ums.service.impl;

import cn.tinybee.ke.biz.ums.entity.UmsLecturer;
import cn.tinybee.ke.biz.ums.mapper.UmsLecturerMapper;
import cn.tinybee.ke.biz.ums.service.IUmsLecturerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师信息表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-07-13
 */
@Service
public class UmsLecturerServiceImpl extends ServiceImpl<UmsLecturerMapper, UmsLecturer> implements IUmsLecturerService {





    @Override
    public boolean turnLecturer(Long empId, Long userId) {


//        int count = this.count(queryWrapper);
//        if (lecturer != null) {
//            throw new BusinessException("该员工已成为讲师");
//        }


        return false;
    }

    @Override
    public UmsLecturer getByEmpId(Long empId) {
        QueryWrapper<UmsLecturer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_id", empId);
        queryWrapper.eq("available", true);
        UmsLecturer lecturer = this.getOne(queryWrapper);
        return lecturer;
    }
}
