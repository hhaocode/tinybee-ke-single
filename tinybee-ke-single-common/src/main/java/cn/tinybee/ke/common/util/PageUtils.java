package cn.tinybee.ke.common.util;

import cn.tinybee.ke.common.entity.PageParam;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
public class PageUtils {

    public static Page pageConvert(PageParam param) {
        if (param == null) {
            param = new PageParam(1, 10);
        }
        if (param.getPage() == null) {
            param.setPage(1);
        }
        if (param.getSize() == null) {
            param.setSize(10);
        }
        return new Page(param.getPage(), param.getSize());
    }
}
