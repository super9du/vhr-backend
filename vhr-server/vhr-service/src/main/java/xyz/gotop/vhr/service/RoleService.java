package xyz.gotop.vhr.service;

import org.springframework.stereotype.Service;
import xyz.gotop.vhr.mapper.RoleMapper;
import xyz.gotop.vhr.model.Role;

import javax.annotation.Resource;
import java.util.List;

/**
 * Demo RoleService
 *
 * @author Wolf-Liu
 * @date 2020/1/27 23:21
 */
@Service
public class RoleService {
    @Resource
    private RoleMapper roleMapper;

    public List<Role> getAll(){
        return roleMapper.getAll();
    }

    public int add(Role role){
        return roleMapper.insertSelective(role);
    }

    public int update(Role role){
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    public int delete(Integer id){
        return roleMapper.deleteByPrimaryKey(id);
    }
}
