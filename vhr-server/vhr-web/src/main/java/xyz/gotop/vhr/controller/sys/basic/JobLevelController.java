package xyz.gotop.vhr.controller.sys.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.gotop.vhr.model.JobLevel;
import xyz.gotop.vhr.model.RespBean;
import xyz.gotop.vhr.service.JobLevelService;

/**
 * Demo JobLevelController
 *
 * @author Wolf-Liu
 * @date 2020/1/20 23:28
 */
@RestController
@RequestMapping("/system/basic/jobLv")
public class JobLevelController {
    @Autowired
    private JobLevelService jobLevelService;

    @GetMapping("/")
    public RespBean getAll() {
        return RespBean.ok(null, jobLevelService.getAll());
    }

    @PostMapping("/")
    public RespBean add(@RequestBody JobLevel jobLevel) {
        jobLevel.setEnabled(true);
        if (jobLevelService.add(jobLevel) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean update(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.update(jobLevel) == 1) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean delete(@PathVariable Integer id) {
        if (jobLevelService.delete(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/")
    public RespBean deleteMany(@RequestBody Integer[] ids) {
        if (jobLevelService.deleteMany(ids) == ids.length) {
            return RespBean.ok("批量删除成功");
        }
        return RespBean.error("批量删除失败");
    }
}
