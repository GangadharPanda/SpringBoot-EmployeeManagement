package com.example.employee_management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_management.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	Employee save(Employee employee);
	
	Employee getReferenceById(Long empid);
	
	void deleteById(Long empid);

	Page<Employee> findByFirstName(String query,
							  Pageable pageable);
}
