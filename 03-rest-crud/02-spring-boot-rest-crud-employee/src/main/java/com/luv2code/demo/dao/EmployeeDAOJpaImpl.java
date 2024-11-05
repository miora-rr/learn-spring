package com.luv2code.demo.dao;

import com.luv2code.demo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl (EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee ", Employee.class);

        // return results
        return theQuery.getResultList();
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {
        // merge: if id==0 insert/save ; else update
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(int id) {
        // find employee by id
        Employee theEmployee = entityManager.find(Employee.class, id);

        // Remove employee
        entityManager.remove(theEmployee);
    }
}
