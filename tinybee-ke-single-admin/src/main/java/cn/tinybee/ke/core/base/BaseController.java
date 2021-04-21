package cn.tinybee.ke.core.base;

import cn.tinybee.ke.admin.system.dto.CacheUserDto;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.util.PageUtils;
import cn.tinybee.ke.core.security.shiro.ShiroUserUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/27
 */
public abstract class BaseController {

    public CacheUserDto getCurrentUser() {
        return (CacheUserDto) ShiroUserUtils.getCurrentUser();
    }

    public Long getCurrentUserId() {
        CacheUserDto currentUser = getCurrentUser();
        if (currentUser == null) {
            return null;
        }
        return currentUser.getId();
    }

    public Operator getOperator() {
        CacheUserDto currentUser = getCurrentUser();
        return new Operator(currentUser.getId(), currentUser.getUsername(), currentUser.getToken(), currentUser.getDeptId(), currentUser.getSuperAdmin());
    }

    public Page pageConvert(PageParam param) {
       return PageUtils.pageConvert(param);
    }
}
