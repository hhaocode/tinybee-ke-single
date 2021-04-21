package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsResource;
import cn.tinybee.ke.biz.cms.mapper.CmsResourceMapper;
import cn.tinybee.ke.biz.cms.service.ICmsResourceService;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.util.AssertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-21
 */
@Service
public class CmsResourceServiceImpl extends ServiceImpl<CmsResourceMapper, CmsResource> implements ICmsResourceService {

    @Autowired
    private Mapper mapper;

    @Override
    public CmsResource saveOrModify(Operator operator, CmsResource param) {
        check (param);
        LocalDateTime now = LocalDateTime.now();
        CmsResource dbRes = null;
        if (param.getId() == null) {
            // 创建
            dbRes = mapper.map(param, CmsResource.class);
            dbRes.setCreateTime(now);
            dbRes.setCreator(operator.getId());

        } else {
            BeanUtils.copyProperties(param, dbRes, "id", "deleted", "creator", "createTime");
        }
        dbRes.setModifier(operator.getId());
        dbRes.setModifyTime(now);
        this.saveOrUpdate(dbRes);
        return dbRes;
    }

    @Override
    public Page<CmsResource> pageResource(Page pageConvert, Integer type) {
        QueryWrapper<CmsResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.orderByDesc("modify_time");
        return this.page(pageConvert, queryWrapper);
    }

    @Override
    public CmsResource get(Long id) {
        return this.getById(id);
    }

    private void check(CmsResource param) {
        switch (param.getType()) {
            case 1:
                AssertUtils.notBlank(param.getContent(), "请输入图文内容");
                break;
            case 2:
                AssertUtils.notNull(param.getVid(), "请先上传音频文件");
                break;
            case 3:
                AssertUtils.notNull(param.getVid(), "请先上传视频文件");
                break;
            case 4:
                AssertUtils.notNull(param.getLiveStartTime(), "请选择直播开始时间");
                AssertUtils.notNull(param.getLiveEndTime(), "请选择直播结束时间");
                break;
            case 5:
                break;
            case 6:
                AssertUtils.notNull(param.getOssId(), "请先已打包好的文件");
                break;
            default:
                throw new BusinessException("资源类型不正确");
        }
    }
}
