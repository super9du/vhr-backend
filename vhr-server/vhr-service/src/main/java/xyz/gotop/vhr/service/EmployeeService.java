package xyz.gotop.vhr.service;

import org.springframework.stereotype.Service;
import xyz.gotop.vhr.mapper.*;
import xyz.gotop.vhr.model.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Demo EmployeeService
 *
 * @author Wolf-Liu
 * @date 2020/2/8 20:07
 */
@Service
public class EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private NationMapper nationMapper;
    @Resource
    private PoliticsstatusMapper politicsstatusMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private JobLevelMapper jobLevelMapper;
    @Resource
    private PositionMapper positionMapper;

    public List<Employee> list(Integer page, Integer size, String keyword) {
        return employeeMapper.list((page - 1) * size, size, keyword);
    }

    public int getTotal(String keyword) {
        return employeeMapper.getTotal(keyword);
    }

    public List<Nation> getAllNations() {
        return nationMapper.selectByExample(new NationExample());
    }

    public List<Politicsstatus> getAllPoliticsstatuses() {
        return politicsstatusMapper.selectByExample(new PoliticsstatusExample());
    }

    public List<Department> getAllDepartments() {
        return departmentMapper.selectByExample(new DepartmentExample());
    }

    public List<JobLevel> getAllJobLevels() {
        return jobLevelMapper.selectByExample(new JobLevelExample());
    }

    public List<Position> getAllPosition(){
        return positionMapper.selectByExample(new PositionExample());
    }

    public int add(List<Employee> employees) {
        return employeeMapper.insertBulk(employees);
    }

    public Employee get(Integer id) {
        return employeeMapper.get(id);
    }
}
