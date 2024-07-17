package com.example.em.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.em.entities.Employee;

@Service
public interface ISearchService {

	public Page<Employee> getEmployeeByName(String query, Integer pageNumber, Integer pageSize) ;
}
