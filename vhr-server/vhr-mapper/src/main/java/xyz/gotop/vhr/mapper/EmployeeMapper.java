package xyz.gotop.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.gotop.vhr.model.Employee;
import xyz.gotop.vhr.model.EmployeeExample;

import java.util.List;

/**
 * EmployeeMapper继承基类
 */
public interface EmployeeMapper extends MyBatisBaseDao<Employee, Integer, EmployeeExample> {
    List<Employee> list(@Param("offset") Integer offset,
                        @Param("size") Integer size,
                        @Param("keyword") String keyword);

    int getTotal(String keyword);

    int insertBulk(List<Employee> employees);

    Employee get(Integer id);
}