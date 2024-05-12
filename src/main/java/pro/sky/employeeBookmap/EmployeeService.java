package pro.sky.employeeBookmap;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class EmployeeService {
    private Map<String, Employee> employeeMap;
    private final int maxNumberOfEmployees = 3;

    public EmployeeService(Map<String, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }

    public Employee add(String firstName, String lastName, int salary, int department)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName, salary,department);
        if (employeeMap.size() == maxNumberOfEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        if (employeeMap.containsKey(employee.buildKey())) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeMap.put(employee.buildKey(), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName, int salary, int department) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (!employeeMap.containsKey(employee.buildKey())) {
            throw new EmployeeNotFoundException();
        }
        return employeeMap.remove(employee.buildKey());
    }

    public Employee find(String firstName, String lastName, int salary, int department) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (!employeeMap.containsKey(employee.buildKey())) {
            throw new EmployeeNotFoundException();
        }
        return employeeMap.get(employee.buildKey());
    }

    public Collection<Employee> print() {
        return employeeMap.values();
    }


}
