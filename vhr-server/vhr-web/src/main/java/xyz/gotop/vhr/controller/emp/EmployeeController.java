package xyz.gotop.vhr.controller.emp;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.gotop.vhr.model.Employee;
import xyz.gotop.vhr.model.PagedRespBean;
import xyz.gotop.vhr.model.RespBean;
import xyz.gotop.vhr.service.EmployeeService;
import xyz.gotop.vhr.utils.EmployeeExcelUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.List;

/**
 * Demo BasicController
 *
 * @author Wolf-Liu
 * @date 2020/2/8 19:32
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/")
    public PagedRespBean list(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer size,
                              String keyword) {
        int maxPageSize = 200;
        if (page < 1 || size < 1 || size > maxPageSize) {
            throw new IllegalArgumentException("请求参数不合法");
        }
        PagedRespBean bean = new PagedRespBean();
        bean.setData(employeeService.list(page, size, keyword));
        bean.setTotal(employeeService.getTotal(keyword));
        return bean;
    }

    @PostMapping("/")
    public RespBean add() {
        //邮件发送测试用户id
        Integer id = 1573;
        Employee employee = employeeService.get(id);
        rabbitTemplate.convertAndSend("go-top.mail.welcome", employee);
        return RespBean.ok("添加成功");
    }

    @GetMapping("excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        String filename = "雇员信息.xls";
        //----- 获取所有雇员信息 -----
        List<Employee> employees = employeeService.list(1, employeeService.getTotal(null), null);
        //----- 获取存储了雇员信息的工作簿对象 -----
        HSSFWorkbook workbook = EmployeeExcelUtils.fillWorkbookWith(employees);

        //----- 设置响应头、写数据 ------
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    @PostMapping("excel")
    public RespBean importExcel(MultipartFile file) {
        //------ 获取上传数据 ------
        List<Employee> employees;
        try {
            employees = EmployeeExcelUtils.fillEmployeeListWith(file.getInputStream());
        } catch (IOException | ParseException e) {
            logger.error("获取文件数据失败", e);
            return RespBean.error("获取文件数据失败");
        }

        //---- 导入数据 -----
        if (employees.size() != employeeService.add(employees)) {
            return RespBean.error("导入数据失败");
        }
        return RespBean.ok("导入成功");
    }

}
