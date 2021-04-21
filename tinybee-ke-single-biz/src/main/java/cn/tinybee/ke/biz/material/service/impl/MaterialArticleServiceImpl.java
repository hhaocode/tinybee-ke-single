package cn.tinybee.ke.biz.material.service.impl;

import cn.tinybee.ke.biz.material.entity.MaterialArticle;
import cn.tinybee.ke.biz.material.mapper.MaterialArticleMapper;
import cn.tinybee.ke.biz.material.service.IMaterialArticleService;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-23
 */
@Service
public class MaterialArticleServiceImpl extends ServiceImpl<MaterialArticleMapper, MaterialArticle> implements IMaterialArticleService {

    @Override
    public Page<MaterialArticle> page(Operator user, Page page) {
        return this.page(page);
    }

    @Override
    public MaterialArticle saveOrModify(Operator operator, MaterialArticle param) {
        LocalDateTime now = LocalDateTime.now();
        if (param.getId() == null) {
            // 新增
            param.setCreateTime(now);
            param.setCreator(operator.getId());
            param.setDeleted(false);
        }
        param.setModifier(operator.getId());
        param.setModifyTime(now);
        this.saveOrUpdate(param);
        return param;
    }

    @Override
    public boolean delete(Operator operator, Long id) {
        MaterialArticle article = this.getById(id);
        // TODO 判断有没有引用
        if (article != null) {
            article.setDeleted(true);
            article.setModifyTime(LocalDateTime.now());
            article.setModifier(operator.getId());
            return this.updateById(article);
        }
        return false;
    }

    @Override
    public Page<MaterialArticle> page(Operator user, Page page, String title) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(title)) {
            queryWrapper.like("title", title);
        }
        return this.page(page, queryWrapper);
    }
}
