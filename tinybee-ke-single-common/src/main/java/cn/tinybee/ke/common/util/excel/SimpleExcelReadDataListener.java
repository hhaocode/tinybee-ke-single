package cn.tinybee.ke.common.util.excel;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/9/28 10:14
 */
@Slf4j
public class SimpleExcelReadDataListener extends AnalysisEventListener<List<Object>> {

    private List<List<Object>> resList;

    private DataHandler dataHandler;

    public SimpleExcelReadDataListener (DataHandler dataHandler) {
        resList = new ArrayList<>();
        this.dataHandler = dataHandler;
    }

    @Override
    public void invoke(List<Object> objects, AnalysisContext analysisContext) {
        log.info("解析到第条数据：{}", JSONUtil.toJsonStr(objects));
        // 可以粉笔处理
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


    public static interface DataHandler {

        Object handle(List<List<Object>> rows);

    }
}
