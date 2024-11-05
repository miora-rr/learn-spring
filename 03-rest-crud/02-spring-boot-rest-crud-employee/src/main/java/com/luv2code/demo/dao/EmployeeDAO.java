package com.luv2code.demo.dao;

import com.luv2code.demo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}
