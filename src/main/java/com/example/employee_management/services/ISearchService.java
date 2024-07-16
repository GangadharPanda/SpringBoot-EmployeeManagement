package com.example.employee_management.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.employee_management.entities.Employee;

@Service
public interface ISearchService {

	public Page<Employee> getEmployeeByName(String query, Integer pageNumber, Integer pageSize) ;
}
