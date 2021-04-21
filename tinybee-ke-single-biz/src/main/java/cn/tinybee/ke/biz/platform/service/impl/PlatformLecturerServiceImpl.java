package cn.tinybee.ke.biz.platform.service.impl;

import cn.tinybee.ke.biz.platform.entity.PlatformLecturer;
import cn.tinybee.ke.biz.platform.mapper.PlatformLecturerMapper;
import cn.tinybee.ke.biz.platform.service.IPlatformLecturerService;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.util.AssertUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 讲师信息表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-28
 */
@Service
public class PlatformLecturerServiceImpl extends ServiceImpl<PlatformLecturerMapper, PlatformLecturer> implements IPlatformLecturerService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrModify(Operator user, PlatformLecturer param) {
        if (param.getId() == null) {
            param.setAvailable(true);
            param.setDeleted(false);
        }
        return this.saveOrUpdate(param);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteById(Operator user, Long id) {
        // 校验 TODO
        PlatformLecturer lecturer = this.getById(id);
        AssertUtils.notNull(lecturer, "讲师不存在");
        lecturer.setDeleted(true);
        return this.updateById(lecturer);
    }

    @Override
    public PlatformLecturer getDetailById(Long id) {
        PlatformLecturer lecturer = this.getById(id);
        return lecturer;
    }
}
