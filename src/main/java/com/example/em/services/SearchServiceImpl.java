package com.example.em.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.em.entities.Employee;
import com.example.em.repositories.EmployeeRepository;

import jakarta.transaction.Transactional;

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
