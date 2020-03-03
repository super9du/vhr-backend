package xyz.gotop.vhr.controller.sys.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.gotop.vhr.model.Position;
import xyz.gotop.vhr.model.RespBean;
import xyz.gotop.vhr.service.PositionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Demo PositionController
 *
 * @author Wolf-Liu
 * @date 2020/1/16 0:39
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PositionService positionService;

    @GetMapping("/")
    public RespBean getAll() {
        final List<Position> all = positionService.getAll();
        if (all.isEmpty()) {
            return RespBean.error("暂无职位信息");
        }
        return RespBean.ok(null, all);
    }

    @PostMapping("/")
    public RespBean add(@RequestBody Position position) {
        logger.debug("【POSITION】{}", position.toString());
        position.setEnabled(true);
        if (positionService.add(position) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean update(@RequestBody Position position) {
        if (positionService.update(position) == 1) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean delete(@PathVariable Integer id) {
        if (positionService.delete(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/")
    public RespBean deleteMany(@RequestBody Integer[] ids){
        if (positionService.deleteMany(ids) == ids.length) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
