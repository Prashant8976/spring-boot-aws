package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Repository
public class EmployeeRepository {

    private final DynamoDbTemplate dynamoDbTemplate;

    public EmployeeRepository(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    public void save(Employee employee) {
        dynamoDbTemplate.save(employee);
    }

    public Employee getById(String empId) {
    	 Key key = Key.builder()
    	            .partitionValue(empId)
    	            .build();
        return dynamoDbTemplate.load(key, Employee.class);
    }
}

