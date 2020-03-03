package xyz.gotop.vhr.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.gotop.vhr.mapper.MenuMapper;
import xyz.gotop.vhr.model.Hr;
import xyz.gotop.vhr.model.Menu;
import xyz.gotop.vhr.model.MenuExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * Demo MenuService
 *
 * @author Wolf-Liu
 * @date 2020/1/5 21:43
 */
@Service
public class MenuService {
    @Resource
    private MenuMapper menuMapper;

    public List<Menu> getMenusByHrId() {
        final Integer hrId = ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return menuMapper.getMenusByHrId(hrId);
    }

    public List<Menu> getAllMenus(){
        return menuMapper.selectByExample(new MenuExample());
    }

    public List<Menu> getAllMenusWithRoles(){
        return menuMapper.getAllMenusWithRoles();
    }

    public List<Menu> getAllMenusWithGrandchildren() {
        return menuMapper.getAllMenusWithGrandchildren();
    }
}
