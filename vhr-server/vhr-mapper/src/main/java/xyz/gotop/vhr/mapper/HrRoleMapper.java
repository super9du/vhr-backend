package xyz.gotop.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.gotop.vhr.model.HrRole;
import xyz.gotop.vhr.model.HrRoleExample;

/**
 * HrRoleMapper继承基类
 */
public interface HrRoleMapper extends MyBatisBaseDao<HrRole, Integer, HrRoleExample> {
    int insertManyHrRolesByHrId(@Param("hrId") Integer hrId, @Param("rids") Integer[] rids);
}