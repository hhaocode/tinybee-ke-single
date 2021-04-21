package cn.tinybee.ke.biz.ums.service.impl;

import cn.tinybee.ke.biz.ums.entity.UmsStudent;
import cn.tinybee.ke.biz.ums.mapper.UmsStudentMapper;
import cn.tinybee.ke.biz.ums.service.IUmsStudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
@Service
public class UmsStudentServiceImpl extends ServiceImpl<UmsStudentMapper, UmsStudent> implements IUmsStudentService {


    @Override
    public UmsStudent getByUsername(String username) {
        QueryWrapper<UmsStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.getOne(queryWrapper);
    }
}
