package xyz.gotop.vhr.utils;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import xyz.gotop.vhr.model.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Demo MyPoiUtils
 *
 * @author Wolf-Liu
 * @date 2020/2/9 19:28
 */
public class HssfWorkbookBuilders {
    private static HssfWorkbookBuilders builder;
    private HSSFWorkbook workbook;

    private HssfWorkbookBuilders() {
        workbook = new HSSFWorkbook();
        workbook.createInformationProperties();
    }

    private HssfWorkbookBuilders(InputStream in) throws IOException {
        workbook = new HSSFWorkbook(in);
    }

    public static HssfWorkbookBuilders builder() {
        return builder = new HssfWorkbookBuilders();
    }

    public HSSFWorkbook build() {
        return workbook;
    }

    //---------- setters ------------

    public HssfWorkbookBuilders setDocSummaryInfo() {
        DocumentSummaryInformation docSummaryInfo = workbook.getDocumentSummaryInformation();
        docSummaryInfo.setCompany("doc: company");
        docSummaryInfo.setCategory("doc: category");
        docSummaryInfo.setContentType("doc: content-type");
        docSummaryInfo.setManager("doc: manager");
        docSummaryInfo.setApplicationVersion(3);
        return this;
    }

    public HssfWorkbookBuilders setSummaryInfo() {
        SummaryInformation summaryInfo = workbook.getSummaryInformation();
        summaryInfo.setAuthor("super9du");
        summaryInfo.setApplicationName("app-name");
        summaryInfo.setComments("comments");
        summaryInfo.setCreateDateTime(new Date());
        summaryInfo.setKeywords("keywords");
        summaryInfo.setSubject("subject");
        summaryInfo.setTitle("员工信息工作簿");
        summaryInfo.setTemplate("template");
        return this;
    }

    public HssfSheetBuilder sheetBuilder(String sheetname, List<?> list) {
        return new HssfSheetBuilder(sheetname, list);
    }

    public HssfSheetBuilder sheetBuilder(List<?> list) {
        return new HssfSheetBuilder(list);
    }


    //-------------------

    public class HssfSheetBuilder {
        private HSSFSheet sheet;
        private List<?> list;
        private int totalColumns;

        public HssfSheetBuilder(List<?> list) {
            this.sheet = workbook.createSheet();
            this.list = list;
        }

        public HssfSheetBuilder(String sheetname, List<?> list) {
            this.sheet = workbook.createSheet(sheetname);
            this.list = list;
        }

        public HssfWorkbookBuilders buildSheet() {
            return builder;
        }

        public HssfSheetBuilder setSheetHead(String[] headnames) {
            totalColumns = headnames.length;
            //---- 设置表头字段 -----
            HSSFRow head = sheet.createRow(0);
            for (int i = 0; i < totalColumns; i++) {
                head.createCell(i).setCellValue(headnames[i]);
            }
            return this;
        }

        public HssfSheetBuilder setSheetHead(String[] headnames, Consumer<HSSFCellStyle> consumer) {
            totalColumns = headnames.length;
            //---- 设置表头字段 -----
            HSSFRow head = sheet.createRow(0);
            for (int i = 0; i < totalColumns; i++) {
                head.createCell(i).setCellValue(headnames[i]);
            }

            //---- 初始化表头样式 ----
            HSSFCellStyle headStyle = workbook.createCellStyle();
            consumer.accept(headStyle);
            //---- 设置表头样式 ----
            head.forEach(cell -> cell.setCellStyle(headStyle));

            return this;
        }

        public HssfSheetBuilder setSheetBody(BiConsumer<HSSFRow, Employee> consumer) {
            for (int i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                consumer.accept(row, (Employee) list.get(i));
            }
            return this;
        }

        public HssfSheetBuilder setSheetBody(Supplier<List<?>> supplier) {
            List<?> values = supplier.get();
            for (int i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                for (int j = 0; j < values.size(); j++) {
                    row.createCell(j).setCellValue((String) values.get(j));
                }
            }
            return this;
        }

        public HssfSheetBuilder setSheetColumnWidth(Supplier<int[]> supplier) {
            int[] widths = supplier.get();
            for (int i = 0; i < totalColumns; i++) {
                sheet.setColumnWidth(i, widths[i]);
            }
            return this;
        }
    }

}
