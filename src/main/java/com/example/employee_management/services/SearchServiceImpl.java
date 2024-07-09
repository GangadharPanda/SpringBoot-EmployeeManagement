package com.example.employee_management.services;

import com.example.employee_management.entities.Employee;
import com.example.employee_management.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SearchServiceImpl implements ISearchService{

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Page<Employee> getEmployeeByName(String query, Integer pageNumber, Integer pageSize) {
		return employeeRepository.findByFirstName(query, PageRequest.of(pageNumber,pageSize));
	}
}
