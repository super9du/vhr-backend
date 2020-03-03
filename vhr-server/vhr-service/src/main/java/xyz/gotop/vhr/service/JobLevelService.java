package xyz.gotop.vhr.service;

import org.springframework.stereotype.Service;
import xyz.gotop.vhr.mapper.JobLevelMapper;
import xyz.gotop.vhr.model.JobLevel;
import xyz.gotop.vhr.model.JobLevelExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * Demo JobLevelService
 *
 * @author Wolf-Liu
 * @date 2020/1/20 23:33
 */
@Service
public class JobLevelService {
    @Resource
    private JobLevelMapper jobLevelMapper;

    public List<JobLevel> getAll(){
        return jobLevelMapper.selectByExample(new JobLevelExample());
    }

    public int add(JobLevel jobLevel){
        jobLevel.setEnabled(true);
        return jobLevelMapper.insertSelective(jobLevel);
    }

    public int delete(Integer id){
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    public int deleteMany(Integer[] ids){
        return jobLevelMapper.deleteManyByIds(ids);
    }

    public int update(JobLevel jobLevel){
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }
}
