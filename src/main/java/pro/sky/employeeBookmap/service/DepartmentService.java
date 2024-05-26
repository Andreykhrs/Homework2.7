package pro.sky.employeeBookmap.service;



import org.springframework.stereotype.Service;
import pro.sky.employeeBookmap.exception.EmployeeNotFoundException;
import pro.sky.employeeBookmap.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
@Service
public class DepartmentService {
        private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findEmployeeWithMaxSalary(int department) {

        return employeeService.print().stream()
                .filter(e -> e.getDepartment() == department)
                .max(comparingInt(e -> e.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);

    }

    public Employee findEmployeeWithMinSalary(int department) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartment() == department)
                .min(comparingInt(e -> e.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Collection<Employee> findEmployeesByDepartment(int department) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> findEmployeesByDepartment() {
        return employeeService.print().stream()
                .collect(groupingBy(Employee::getDepartment));
    }


}
