package cn.tinybee.ke.test;

import cn.tinybee.ke.biz.material.entity.MaterialFile;
import com.baomidou.mybatisplus.annotation.TableField;
import java.lang.reflect.Field;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/3/10 16:35
 */
public class GenerateColumn {

    public static void main(String[] args) {
        Class clas = MaterialFile.class;
        System.out.println(generaorSame(clas, false));
    }

    public static String generaorSame(Class clas, boolean ignoreTransient) {

        Field[] declaredFields = clas.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field declaredField : declaredFields) {
            TableField annotation = declaredField.getAnnotation(TableField.class);
            if (annotation != null && !annotation.exist()) {
                continue;
            }
            String name = declaredField.getName();
            if ("id".equals(name)) {
                sb.append(String.format("<id column=\"%s\" property=\"%s\"></id>", name, name)).append("\n");
            } else {
                sb.append(String.format("<result column=\"%s\" property=\"%s\"></result>", name, name)).append("\n");
            }
        }
//        }
        return sb.toString();
//        System.out.println(sb.toString());
    }
}
