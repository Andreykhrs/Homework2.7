package pro.sky.employeeBookmap;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;
    private int department;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeWithMaxSalary(int department) {
        this.department = department;
        return employeeService.print().stream()
                .filter(e -> e.getDepartment() == department)
                .max(comparingInt(e -> e.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);

    }

    @Override
    public Employee findEmployeeWithMinSalary(int department) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartment() == department)
                .min(comparingInt(e -> e.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Collection<Employee> findEmployeesByDepartment(int department) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> findEmployeesByDepartment() {
        return employeeService.print().stream()
                .collect(groupingBy(Employee::getDepartment));
    }
}
