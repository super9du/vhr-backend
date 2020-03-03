package xyz.gotop.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.gotop.vhr.model.MenuRole;
import xyz.gotop.vhr.model.MenuRoleExample;

import java.util.List;

/**
 * MenuRoleMapper继承基类
 */
public interface MenuRoleMapper extends MyBatisBaseDao<MenuRole, Integer, MenuRoleExample> {
     List<Integer> getMidsByRid(Integer rid);

    int insertMenuRoles(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}