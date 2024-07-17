package com.example.em.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.em.entities.Employee;
import com.example.em.repositories.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Long empId) {
		 employeeRepository.deleteById(empId);
	}

	@Override
	public Optional<Employee> getEmployee(Long empId) {
		return employeeRepository.findById(empId);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

}
