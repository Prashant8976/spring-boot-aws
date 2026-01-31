package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;
import com.example.demo.security.KmsService;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final DynamoDbTemplate dynamoDbTemplate;

    private final KmsService kmsService;
    

    public void save(Employee employee) {
    	employee.setEmail(kmsService.encrypt(employee.getEmail()));
        dynamoDbTemplate.save(employee);
    }

    public Employee getById(String empId) {
    	 Key key = Key.builder()
    	            .partitionValue(empId)
    	            .build();
    	 Employee employee = dynamoDbTemplate.load(key, Employee.class);
    	 System.out.println("Encrypted Email: " + kmsService.decrypt(employee.getEmail()));
    	 employee.setEmail(kmsService.decrypt(employee.getEmail()));
    	 
    	 
         return employee;
    }
}

