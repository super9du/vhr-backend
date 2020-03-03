package xyz.gotop.vhr.controller.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.gotop.vhr.model.Menu;
import xyz.gotop.vhr.service.MenuService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Demo SystemConfig
 *
 * @author Wolf-Liu
 * @date 2020/1/5 23:05
 */
@RequestMapping("system/config")
@RestController
public class SystemConfig {
    @Resource
    private MenuService menuService;

    @GetMapping("menu")
    public List<Menu> getMenuList(){
        return menuService.getMenusByHrId();
    }
}
