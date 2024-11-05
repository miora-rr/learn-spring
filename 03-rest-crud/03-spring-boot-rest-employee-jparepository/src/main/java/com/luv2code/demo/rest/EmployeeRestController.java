package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Employee;
import com.luv2code.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);

        // Sad: Java does not support using throw directly in a ternary expression
        if (theEmployee == null) {
            throw new RuntimeException("Employee not found - " + employeeId);
        }

        return theEmployee;
    }

    @PostMapping("/employees/")
    public Employee createEmployee(@RequestBody Employee employee) {
        // in case id is passed in JSON, set it to 0, allowing us to save the new item
        employee.setId(0);

        return employeeService.save(employee);
    }

    @PutMapping("/employees/")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }
}
