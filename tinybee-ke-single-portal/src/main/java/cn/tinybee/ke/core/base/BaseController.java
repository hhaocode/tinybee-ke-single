package cn.tinybee.ke.core.base;

import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.util.PageUtils;
import cn.tinybee.ke.portal.core.security.ArcusCacheUser;
import cn.tinybee.ke.portal.core.security.ArcusUserUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
public abstract class BaseController {


    /**
     * 获取当前用户
     * @return
     */
    public ArcusCacheUser getCurrentUser() {
        return ArcusUserUtils.getCurrentUser();
    }

    /**
     * 获取当前用户ID
     * @return
     */
    public Long getCurrentUserId() {
        return ArcusUserUtils.getCurrentUserId();
    }

    public Page pageConvert(PageParam param) {
        return PageUtils.pageConvert(param);
    }

    public Operator getOptUser () {
        ArcusCacheUser currentUser = this.getCurrentUser();
        if (currentUser == null) {
            return null;
        }
        Operator user = new Operator();
        user.setId(currentUser.getId());
        return user;
    }
}
