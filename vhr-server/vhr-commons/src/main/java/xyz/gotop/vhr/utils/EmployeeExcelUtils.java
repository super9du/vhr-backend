package xyz.gotop.vhr.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.gotop.vhr.model.Employee;
import xyz.gotop.vhr.service.EmployeeService;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Employee 的 Excel 工具类
 *
 * @author Wolf-Liu
 * @date 2020/2/14 14:50
 */
@Component
public class EmployeeExcelUtils {
    private static EmployeeService employeeService;

    public static final String[] HEADNAMES = {
            "编号", "工号", "姓名", "性别", "出生年月", "身份证号", "婚姻状况", "民族", "籍贯",
            "政治面貌", "邮箱账号", "电话号码", "家庭住址", "所属部门", "职称", "职级", "职位",
            "聘用形式", "学历", "专业", "毕业院校", "入职日期", "转正日期", "在职状态", "合约期限（年）",
            "合同期始", "合同期止", "工龄", "离职日期"
    };

    /**
     * 使用 @code{List<Employee>} 填充 HSSFWorkbook 对象
     */
    public static HSSFWorkbook fillWorkbookWith(List<Employee> employees) {
        //------ 创建工作簿 -------
        return HssfWorkbookBuilders.builder()
                .setDocSummaryInfo()
                .setSummaryInfo()
                .sheetBuilder("雇员信息", employees)
                .setSheetHead(HEADNAMES, headStyle -> {
                    headStyle.setBorderBottom(BorderStyle.MEDIUM);
                    headStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                })
                .setSheetBody(biConsumer())
                .buildSheet()
                .build();
    }

    public static List<Employee> fillEmployeeListWith(InputStream in) throws IOException, ParseException {
        List<Employee> employees = new LinkedList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(in);
        int numberOfSheets = workbook.getNumberOfSheets();

        for (int i = 0; i < numberOfSheets; i++) {
            HSSFSheet sheet = workbook.getSheetAt(i);
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            for (int j = 1; j < numberOfRows; j++) {
                HSSFRow row = sheet.getRow(j);
                if (row == null || row.getCell(1) == null || row.getCell(2) == null) {
                    continue;
                }
                Employee employee = new Employee();
                Iterator<Cell> iterator = row.cellIterator();
                while (iterator.hasNext()) {
                    fillEmployee(iterator.next(), employee);
                }
                employees.add(employee);
            }
        }
        return employees;
    }

    /**
     * 用于设置工作表内容的「二元消费者」方法
     */
    static BiConsumer<HSSFRow, Employee> biConsumer() {
        return (row, employee) -> {
            row.createCell(0).setCellValue(employee.getId());
            row.createCell(1).setCellValue(employee.getWorkid());
            row.createCell(2).setCellValue(employee.getName());
            row.createCell(3).setCellValue(employee.getGender());
            row.createCell(4).setCellValue(DateUtils.dateFormat(employee.getBirthday()));
            row.createCell(5).setCellValue(employee.getIdcard());
            row.createCell(6).setCellValue(employee.getWedlock());//婚姻状况
            row.createCell(7).setCellValue(employee.getNation().getName());
            row.createCell(8).setCellValue(employee.getNativeplace());//籍贯
            row.createCell(9).setCellValue(employee.getPoliticsstatus().getName());//政治面貌
            row.createCell(10).setCellValue(employee.getEmail());
            row.createCell(11).setCellValue(employee.getPhone());
            row.createCell(12).setCellValue(employee.getAddress());
            row.createCell(13).setCellValue(employee.getDepartment().getName());
            row.createCell(14).setCellValue(employee.getJoblevel().getName());//职称
            row.createCell(15).setCellValue(employee.getJoblevel().getTitlelevel());//职级
            row.createCell(16).setCellValue(employee.getPosition().getName());
            row.createCell(17).setCellValue(employee.getEngageform());//聘用形式
            row.createCell(18).setCellValue(employee.getTiptopdegree());//最高学历
            row.createCell(19).setCellValue(employee.getSpecialty());//专业
            row.createCell(20).setCellValue(employee.getSchool());
            row.createCell(21).setCellValue(DateUtils.dateFormat(employee.getBegindate()));//入职日期
            row.createCell(22).setCellValue(DateUtils.dateFormat(employee.getConversiontime()));//转正日期
            row.createCell(23).setCellValue(employee.getWorkstate());//在职状态
            row.createCell(24).setCellValue(employee.getContractterm());//合约期（年）
            row.createCell(25).setCellValue(DateUtils.dateFormat(employee.getBegincontract()));//合同期始
            row.createCell(26).setCellValue(DateUtils.dateFormat(employee.getEndcontract()));//合同期止
            row.createCell(27).setCellValue(employee.getWorkage() == null ? 0 : employee.getWorkage());
            row.createCell(28).setCellValue(DateUtils.dateFormat(employee.getNotworkdate()));//离职日期
        };
    }

    static void fillEmployee(Cell cell, Employee employee) throws ParseException {
        switch (cell.getColumnIndex()) {
            case 1:
                employee.setWorkid(cell.getStringCellValue());
                break;
            case 2:
                employee.setName(cell.getStringCellValue());
                break;
            case 3:
                employee.setGender(cell.getStringCellValue());
                break;
            case 4:
                if (cell.getCellType() == CellType.STRING) {
                    employee.setBirthday(DateUtils.parse(cell.getStringCellValue()));
                } else {
                    employee.setBirthday(cell.getDateCellValue());
                }
                break;
            case 5:
                employee.setIdcard(cell.getStringCellValue());
                break;
            case 6:
                employee.setWedlock(cell.getStringCellValue());
                break;
            case 7:
                employeeService.getAllNations().forEach(nation -> {
                    if (nation.getName().equals(cell.getStringCellValue())) {
                        employee.setNation(nation);
                    }
                });
                break;
            case 8:
                employee.setNativeplace(cell.getStringCellValue());
                break;
            case 9:
                employeeService.getAllPoliticsstatuses().forEach(ps -> {
                    if (ps.getName().equals(cell.getStringCellValue())) {
                        employee.setPoliticsstatus(ps);
                    }
                });
                break;
            case 10:
                employee.setEmail(cell.getStringCellValue());
                break;
            case 11:
                employee.setPhone(cell.getStringCellValue());
                break;
            case 12:
                employee.setAddress(cell.getStringCellValue());
                break;
            case 13:
                employeeService.getAllDepartments().forEach(department -> {
                    if (department.getName().equals(cell.getStringCellValue())) {
                        employee.setDepartment(department);
                    }
                });
                break;
            case 14:
                //序列号为14的列为职称（joblevel的属性），与序列号为15的列一并设置
                employeeService.getAllJobLevels().forEach(jobLevel -> {
                    if (jobLevel.getName().equals(cell.getStringCellValue())) {
                        employee.setJoblevel(jobLevel);
                    }
                });
                break;
            case 16:
                employeeService.getAllPosition().forEach(position -> {
                    if (position.getName().equals(cell.getStringCellValue())) {
                        employee.setPosition(position);
                    }
                });
                break;
            case 17:
                employee.setEngageform(cell.getStringCellValue());
                break;
            case 18:
                employee.setTiptopdegree(cell.getStringCellValue());
                break;
            case 19:
                employee.setSpecialty(cell.getStringCellValue());
                break;
            case 20:
                employee.setSchool(cell.getStringCellValue());
                break;
            case 21:
                if (cell.getCellType() == CellType.STRING) {
                    employee.setBegindate(DateUtils.parse(cell.getStringCellValue()));
                } else {
                    employee.setBirthday(cell.getDateCellValue());
                }
                break;
            case 22:
                if (cell.getCellType() == CellType.STRING) {
                    employee.setConversiontime(DateUtils.parse(cell.getStringCellValue()));
                } else {
                    employee.setBirthday(cell.getDateCellValue());
                }
                break;
            case 23:
                employee.setWorkstate(cell.getStringCellValue());
                break;
            case 24:
                employee.setContractterm(cell.getNumericCellValue());
                break;
            case 25:
                if (cell.getCellType() == CellType.STRING) {
                    employee.setBegincontract(DateUtils.parse(cell.getStringCellValue()));
                } else {
                    employee.setBirthday(cell.getDateCellValue());
                }
                break;
            case 26:
                if (cell.getCellType() == CellType.STRING) {
                    employee.setEndcontract(DateUtils.parse(cell.getStringCellValue()));
                } else {
                    employee.setBirthday(cell.getDateCellValue());
                }
                break;
            case 27:
                double value = cell.getNumericCellValue();
                employee.setWorkage((Double.isNaN(value) ? null : ((int) value)));
                break;
            case 28:
                if (cell.getCellType() == CellType.STRING) {
                    employee.setNotworkdate(DateUtils.parse(cell.getStringCellValue()));
                } else {
                    employee.setBirthday(cell.getDateCellValue());
                }
                break;
            default:
                break;
        }
    }


    //---------- setter -----------

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        EmployeeExcelUtils.employeeService = employeeService;
    }

}
