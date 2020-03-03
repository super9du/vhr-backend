package xyz.gotop.vhr.mapper;

import xyz.gotop.vhr.model.Role;
import xyz.gotop.vhr.model.RoleExample;

import java.util.List;

/**
 * RoleMapper继承基类
 */
public interface RoleMapper extends MyBatisBaseDao<Role, Integer, RoleExample> {
    List<Role> getAll();
}