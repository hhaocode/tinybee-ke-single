package cn.tinybee.ke.common.util.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/9/28 9:53
 */
public class ExcelUtils {

    /**
     *
     * @param response
     * @param clazz
     * @param data
     * @param filename
     * @throws IOException
     */
    public static void write2Web (HttpServletResponse response, Class<?> clazz, List data, String filename) throws IOException {
        Set<String> includeColumnFiledNames = new HashSet<>();
        for (Field declaredField : clazz.getDeclaredFields()) {
            ExcelProperty annotation = declaredField.getAnnotation(ExcelProperty.class);
            if (annotation != null) {
                includeColumnFiledNames.add(declaredField.getName());
            }
        }

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), clazz)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .includeColumnFiledNames(includeColumnFiledNames)
                .sheet("数据").doWrite(data);

    }

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static List<Object> read4MultipartFile(MultipartFile file) throws IOException {

//        ExcelReader excelReader = null;
//
//
//        EasyExcel.read
//
//        excelReader = EasyExcel.read(file.getInputStream(), List.class, ).build();
//
//
//        ReadSheet readSheet = EasyExcel.readSheet(0).build();
//
//
//
//        ExcelReader read = excelReader.read(readSheet);
//
//        read.
//
//        List<Object> objects = read.doReadAllSync();
//        return objects;
        return null;
    }
}
