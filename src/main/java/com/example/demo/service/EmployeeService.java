package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.security.KmsService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final KmsService kmsService;

    private final EmployeeRepository repository;

    public void create(Employee employee) {
    	
    	employee.setEmail(kmsService.encrypt(employee.getEmail()));
        repository.save(employee);
    }

    public Employee get(String id) {
    	Employee employee = repository.getById(id);
    	employee.setEmail(kmsService.decrypt(employee.getEmail()));
        return employee;
    }
}

