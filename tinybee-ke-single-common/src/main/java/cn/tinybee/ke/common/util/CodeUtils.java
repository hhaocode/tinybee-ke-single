package cn.tinybee.ke.common.util;

import java.util.Random;

/**
 * @author hao.huang
 * @description
 * @date 2020/4/9
 */
public class CodeUtils {

    public static String  randomCode(int length) {
        if (length < 0) {
            throw  new IllegalArgumentException("length must > 0 ");
        }
        int pow = (int) Math.pow(10, length);
        int max =  pow - 1;
        int min = pow / 10;
        Random random = new Random();
        int i = random.nextInt(max);
        return String.valueOf(i < min ? min * i : i);
    }
}
