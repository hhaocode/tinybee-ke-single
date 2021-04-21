package cn.tinybee.ke.biz.ums.service;

import cn.tinybee.ke.biz.ums.entity.UmsStudent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
public interface IUmsStudentService extends IService<UmsStudent> {

    UmsStudent getByUsername(String username);

}
