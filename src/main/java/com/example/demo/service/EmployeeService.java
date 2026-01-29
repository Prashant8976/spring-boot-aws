package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public void create(Employee employee) {
        repository.save(employee);
    }

    public Employee get(String id) {
        return repository.getById(id);
    }
}

