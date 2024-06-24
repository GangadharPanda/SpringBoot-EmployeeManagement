package com.example.employee_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.employee_management.entities.Employee;

@Service
public interface IEmployeeService {
	
	public Employee createEmployee(Employee employee) ;
	
	public Employee updateEmployee(Employee employee) ;
	
	public void deleteEmployee(Long empId) ;
	
	public Optional<Employee> getEmployee(Long empId) ;
	
	public List<Employee> getAllEmployee() ;

}
