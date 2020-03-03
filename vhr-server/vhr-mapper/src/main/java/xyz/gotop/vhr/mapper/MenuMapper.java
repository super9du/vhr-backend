package xyz.gotop.vhr.mapper;

import xyz.gotop.vhr.model.Menu;
import xyz.gotop.vhr.model.MenuExample;

import java.util.List;

/**
 * MenuMapper继承基类
 */
public interface MenuMapper extends MyBatisBaseDao<Menu, Integer, MenuExample> {
    List<Menu> getMenusByHrId(Integer hrId);

    List<Menu> getAllMenusWithRoles();

    List<Menu> getAllMenusWithGrandchildren();
}