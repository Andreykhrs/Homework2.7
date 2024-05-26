package pro.sky.employeeBookmap.service;

import org.springframework.stereotype.Service;

import pro.sky.employeeBookmap.exception.EmployeeAlreadyAddedException;
import pro.sky.employeeBookmap.exception.EmployeeNotFoundException;
import pro.sky.employeeBookmap.exception.EmployeeStorageIsFullException;
import pro.sky.employeeBookmap.model.Employee;


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
        if (employeeMap.containsKey(buildKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeMap.put(buildKey(firstName, lastName), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employeeMap.containsKey(buildKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }
        return employeeMap.remove(buildKey(firstName, lastName));
    }

    public Employee find(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employeeMap.containsKey(buildKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }
        return employeeMap.get(buildKey(firstName, lastName));
    }

    public Collection<Employee> print() {
        return employeeMap.values();
    }

    public String buildKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }


}
