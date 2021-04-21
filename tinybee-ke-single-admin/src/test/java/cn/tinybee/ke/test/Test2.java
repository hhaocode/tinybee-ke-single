package cn.tinybee.ke.test;

import cn.hutool.crypto.SecureUtil;
import org.junit.Test;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/1/13 9:35
 */
public class Test2 {

    @Test
    public void adminPwd () {
        System.out.println(SecureUtil.md5("tinybeeAdmin@2020"));
    }
}
