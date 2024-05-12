package pro.sky.employeeBookmap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findEmployeeWithMaxSalary(int departmentId);
    Employee findEmployeeWithMinSalary(int department);
    Collection<Employee> findEmployeesByDepartment(int department);
    Map<Integer, List<Employee>> findEmployeesByDepartment();
}
