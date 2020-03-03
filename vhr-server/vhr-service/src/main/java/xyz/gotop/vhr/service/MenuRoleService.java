package xyz.gotop.vhr.service;

import org.springframework.stereotype.Service;
import xyz.gotop.vhr.mapper.MenuRoleMapper;
import xyz.gotop.vhr.model.MenuRole;
import xyz.gotop.vhr.model.MenuRoleExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * Demo MenuRoleService
 *
 * @author Wolf-Liu
 * @date 2020/1/28 16:25
 */
@Service
public class MenuRoleService {
    @Resource
    private MenuRoleMapper menuRoleMapper;

    public List<Integer> getMidsByRid(Integer rid) {
        return menuRoleMapper.getMidsByRid(rid);
    }

    public int deleteByRid(Integer rid) {
        MenuRoleExample example = new MenuRoleExample();
        example.createCriteria().andRidEqualTo(rid);
        return menuRoleMapper.deleteByExample(example);
    }

    public int addMenuRoles(Integer rid, Integer[] mids) {
        return menuRoleMapper.insertMenuRoles(rid, mids);
    }
}
