package com.example.employee_management.services;

import com.example.employee_management.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ISearchService {

	public Page<Employee> getEmployeeByName(String query, Integer pageNumber, Integer pageSize) ;
}
