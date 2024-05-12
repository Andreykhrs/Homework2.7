package pro.sky.employeeBookmap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("max-salary")
    public  Employee findEmployeeWithMaxSalary(@RequestParam(required = false) int department) {
        return departmentService.findEmployeeWithMaxSalary(department);
    }

    @GetMapping("min-salary")
    public  Employee findEmployeeWithMinSalary(@RequestParam(required = false) int department) {
        return departmentService.findEmployeeWithMinSalary(department);
    }

    @GetMapping(value = "all", params = {"department"})
    public Collection<Employee> findEmployeesByDepartment(@RequestParam(required = false) int department){
        return departmentService.findEmployeesByDepartment(department);
    }
    @GetMapping("all")
    public Map<Integer, List<Employee>> findEmployeesByDepartment(){
        return departmentService.findEmployeesByDepartment();
    }



}
