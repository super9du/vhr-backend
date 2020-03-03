package xyz.gotop.vhr.mapper;

import xyz.gotop.vhr.model.Hr;
import xyz.gotop.vhr.model.HrExample;
import xyz.gotop.vhr.model.Role;

import java.util.List;

/**
 * HrMapper继承基类
 */
public interface HrMapper extends MyBatisBaseDao<Hr, Integer, HrExample> {
    Hr loadUserByUsername(String username);

    List<Role> getRolesByHrId(Integer id);

    List<Hr> getHrsWithRoles(Integer hrId);
}