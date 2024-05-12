package pro.sky.employeeBookmap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam(required = false) String firstName,
                        @RequestParam(required = false) String lastName,
                        @RequestParam(required = false) Integer salary,
                        @RequestParam(required = false) Integer department) {
        return employeeService.add(firstName, lastName, salary, department);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName,
                           @RequestParam(required = false) Integer salary,
                           @RequestParam(required = false) Integer department) {
        return employeeService.remove(firstName, lastName, salary,department);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam(required = false) String firstName,
                         @RequestParam(required = false) String lastName,
                         @RequestParam(required = false) Integer salary,
                         @RequestParam(required = false) Integer department) {
        return employeeService.find(firstName, lastName, salary, department);
    }

    @GetMapping()
    public Collection<Employee> print() {
        return employeeService.print();
    }
}