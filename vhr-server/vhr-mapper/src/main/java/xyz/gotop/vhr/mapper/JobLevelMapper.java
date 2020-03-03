package xyz.gotop.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.gotop.vhr.model.JobLevel;
import xyz.gotop.vhr.model.JobLevelExample;

/**
 * JoblevelMapper继承基类
 */
public interface JobLevelMapper extends MyBatisBaseDao<JobLevel, Integer, JobLevelExample> {
    int deleteManyByIds(@Param("ids") Integer[] ids);
}