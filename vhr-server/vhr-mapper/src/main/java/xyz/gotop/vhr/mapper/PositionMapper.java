package xyz.gotop.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.gotop.vhr.model.Position;
import xyz.gotop.vhr.model.PositionExample;

/**
 * PositionMapper继承基类
 */
public interface PositionMapper extends MyBatisBaseDao<Position, Integer, PositionExample> {
    int deleteManyByIds(@Param("ids") Integer[] ids);
}