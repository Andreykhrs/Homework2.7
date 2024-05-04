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

    public Employee add(String firstName, String lastName)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName);
        if (employeeMap.size() == maxNumberOfEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        if (employeeMap.containsKey(employee.getKey())) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeMap.put(employee.getKey(), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employeeMap.containsKey(employee.getKey())) {
            throw new EmployeeNotFoundException();
        }
        return employeeMap.remove(employee.getKey());
    }

    public Employee find(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employeeMap.containsKey(employee.getKey())) {
            throw new EmployeeNotFoundException();
        }
        return employeeMap.get(employee.getKey());
    }

    public Collection<Employee> print() {
        return employeeMap.values();
    }


}
