package cn.tinybee.ke.common.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/23 15:16
 */
public final class IdUtils {

    private static final Snowflake FILE_NAME_SNOWFLAKE = IdUtil.createSnowflake( 1, 1); //

    private static final Snowflake ORDER_NO_SNOWFLAKE = IdUtil.createSnowflake( 2, 1);

    private static final Snowflake PAYMENT_NO_SNOWFLAKE = IdUtil.createSnowflake( 3, 1);

    public static String fileNameId () {
        return FILE_NAME_SNOWFLAKE.nextIdStr();
    }


    public static String orderNo () {
        return ORDER_NO_SNOWFLAKE.nextIdStr();
    }

    public static String paymentNo() {
        return PAYMENT_NO_SNOWFLAKE.nextIdStr();
    }

}
