package xyz.gotop.vhr.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xyz.gotop.vhr.model.Hr;
import xyz.gotop.vhr.model.RespBean;
import xyz.gotop.vhr.service.HrService;
import xyz.gotop.vhr.service.RoleService;

/**
 * Demo Hr
 *
 * @author Wolf-Liu
 * @date 2020/1/30 12:03
 */
@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public RespBean list() {
        return RespBean.ok(null, hrService.getHrsWithRoles());
    }

    @PostMapping("/")
    public RespBean add(@RequestBody Hr hr) {
        if (hrService.add(hr) != 1) {
            return RespBean.error("添加失败");
        }
        return RespBean.ok("添加成功");
    }

    @PutMapping("/")
    public RespBean update(@RequestBody Hr hr) {
        if (hrService.update(hr) != 1) {
            return RespBean.error("更新失败");
        }
        return RespBean.ok("更新成功");
    }

    @DeleteMapping("/{hrId}")
    public RespBean delete(@PathVariable Integer hrId) {
        if (hrService.delete(hrId) != 1) {
            return RespBean.error("删除失败");
        }
        return RespBean.ok("删除成功");
    }

    @PutMapping("/{hrId}/role")
    public RespBean updateRolesOfHr(@PathVariable Integer hrId, @RequestBody Integer[] rids) {
        if (!hrService.updateRolesOfHr(hrId, rids)) {
            return RespBean.error("更新失败");
        }
        return RespBean.ok("更新成功");
    }

    @GetMapping("/roles")
    public RespBean getAllRoles() {
        return RespBean.ok(null, roleService.getAll());
    }
}
