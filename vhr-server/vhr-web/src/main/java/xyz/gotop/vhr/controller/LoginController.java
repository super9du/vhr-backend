package xyz.gotop.vhr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.gotop.vhr.model.RespBean;

/**
 * Demo LoginController
 *
 * @author Wolf-Liu
 * @date 2019/12/22 22:28
 */
@RestController
public class LoginController {

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("login")
    public RespBean login() {
        return RespBean.error("尚未登录，请登录");
    }

    @GetMapping("/employee/basic/hello")
    public String test1() {
        return "/employee/basic/hello";
    }

    @GetMapping("statistics/personnel/hello")
    public String test2() {
        return "statistics/personnel/hello";
    }
}
