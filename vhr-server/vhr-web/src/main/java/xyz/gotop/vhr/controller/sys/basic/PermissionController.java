package xyz.gotop.vhr.controller.sys.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xyz.gotop.vhr.model.Menu;
import xyz.gotop.vhr.model.RespBean;
import xyz.gotop.vhr.model.Role;
import xyz.gotop.vhr.service.MenuRoleService;
import xyz.gotop.vhr.service.MenuService;
import xyz.gotop.vhr.service.RoleService;

import java.util.List;

/**
 * Demo PermissonController
 *
 * @author Wolf-Liu
 * @date 2020/1/27 22:09
 */
@RestController
@RequestMapping("/system/basic/perm")
public class PermissionController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuRoleService menuRoleService;

    @GetMapping("/")
    public RespBean getAllRoles() {
        final List<Role> roles = roleService.getAll();
        return RespBean.ok(null, roles);
    }

    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role) {
        if (roleService.add(role) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean updateRole(@RequestBody Role role) {
        if (roleService.update(role) == 1) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteRole(@PathVariable Integer id) {
        if (roleService.delete(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @GetMapping("menus")
    public RespBean getAllMenusWithGrandchildren() {
        final List<Menu> menus = menuService.getAllMenusWithGrandchildren();
        if (menus.isEmpty()) {
            return RespBean.error("获取「权限列表」失败");
        }
        return RespBean.ok(null, menus);
    }

    @GetMapping("mids")
    public RespBean getAllMids() {
        return RespBean.ok(null, menuRoleService.getMidsByRid(null));
    }

    @GetMapping("mids/{rid}")
    public RespBean getMidsByRid(@PathVariable Integer rid) {
        return RespBean.ok(null, menuRoleService.getMidsByRid(rid));
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping("menus/{rid}")
    public RespBean updateMidsOfRole(@PathVariable Integer rid, @RequestBody Integer[] mids) {
        try {
            menuRoleService.deleteByRid(rid);
            if (mids.length > 0) {
                menuRoleService.addMenuRoles(rid, mids);
            }
        } catch (Exception e) {
            logger.error("「角色权限」更新失败", e);
            return RespBean.error("「角色权限」更新失败");
        }
        return RespBean.ok("更新成功");
    }
}
