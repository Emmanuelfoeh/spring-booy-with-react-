package com.emmanuelfo.lesson1.restControllers;

import com.emmanuelfo.lesson1.model.Employee;
import com.emmanuelfo.lesson1.repositroy.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin("*")
public class EmployeesRestController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping()
    public List<Employee> getAllEmployer(){
        return employeeRepository.findAll();
    }
    @PostMapping()
    public void addEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
    }
}
