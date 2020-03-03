package xyz.gotop.vhr.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.gotop.vhr.model.RespBean;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Demo SQLExceptionHandler
 *
 * @author Wolf-Liu
 * @date 2020/1/18 23:19
 */
@ControllerAdvice
public class SQLExceptionHandler {

    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public RespBean IntegrityConstraintViolation(Exception e) {
        System.out.println(e.getMessage());
        return RespBean.error("数据库主键约束异常");
    }
}
