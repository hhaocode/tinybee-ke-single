package cn.tinybee.ke.common.util.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/9/16 12:30
 */
public class PDFDocument {

    private Document document;

    Rectangle pageSize = PageSize.A4;

    public PDFDocument () {
        document = new Document(pageSize);
    }
}
