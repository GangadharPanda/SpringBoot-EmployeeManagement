package com.example.em.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.em.dtos.ApiResponseDTO;
import com.example.em.dtos.EmployeeDTO;
import com.example.em.entities.Employee;
import com.example.em.services.IAuthServiceWithToken;
import com.example.em.services.IEmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("employee/v1")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IAuthServiceWithToken authService;

	/**
	 * 
	 * 
	 * @param employeeDTO
	 * @return the employeeDTO object but we need all the values like status code
	 *         and all for that we would need to wrap it up
	 *         ResponseEntity<EmployeeDTO> (employeeDTO, Status Code)
	 */

	@PostMapping("/create")
	public ResponseEntity<ApiResponseDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		employeeDTO.setId(null);
		Employee employee = convertToEmployee(employeeDTO);
		ApiResponseDTO apiResponseDTO = new ApiResponseDTO("Created Successfully ",
				convertToEmployeeDTO(employeeService.createEmployee(employee)), null);
		return new ResponseEntity<ApiResponseDTO>(apiResponseDTO, HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<ApiResponseDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		if (employeeDTO.getId() == null || !employeeService.getEmployee(employeeDTO.getId()).isPresent()) {
			throw new IllegalArgumentException("Invalid or missing Id. Cannot update non-existing employee.");
		}

		Employee employee = convertToEmployee(employeeDTO);
		ApiResponseDTO apiResponseDTO = new ApiResponseDTO("Updated Suceesfully",
				convertToEmployeeDTO(employeeService.updateEmployee(employee)), null);
		return new ResponseEntity<ApiResponseDTO>(apiResponseDTO, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ApiResponseDTO> getEmployee(@PathVariable("id") Long empId) {
		Optional<Employee> employeeOptional = employeeService.getEmployee(empId);
		ApiResponseDTO apiResponseDTO = null;
		if (!employeeOptional.isPresent()) {
			throw new IllegalArgumentException("Invalid Id. Cannot fetch non-existing employee.");
		} else {
			apiResponseDTO = new ApiResponseDTO("fetched successfully", employeeOptional.get(), null);
		}
		return new ResponseEntity<ApiResponseDTO>(apiResponseDTO, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<ApiResponseDTO> getAllEmployee() {
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		List<Employee> employees = employeeService.getAllEmployee();
		for (Employee employee : employees) {
			employeeDTOs.add(convertToEmployeeDTO(employee));
		}

		ApiResponseDTO apiResponseDTO = new ApiResponseDTO("fetched successfully", employeeDTOs, null);
		return new ResponseEntity<ApiResponseDTO>(apiResponseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponseDTO> deleteEmployee(@PathVariable("id") Long empId,
			@RequestHeader("Authorization") String token) {

		Optional<Employee> employeeOptional = employeeService.getEmployee(empId);
		if (empId == null || !employeeOptional.isPresent()) {
			throw new IllegalArgumentException("Invalid Id. Cannot delete non-existing employee.");
		}
		boolean isValidToken = authService.isValidToken(token, employeeOptional.get().getEmail());
		if (!isValidToken) {
			throw new IllegalArgumentException("Invalid session. Please re login.");
		}
		employeeService.deleteEmployee(empId);
		ApiResponseDTO apiResponseDTO = new ApiResponseDTO("Deleted successfully", null, null);
		return new ResponseEntity<ApiResponseDTO>(apiResponseDTO, HttpStatus.OK);
	}

	private EmployeeDTO convertToEmployeeDTO(Employee employee) {
		return EmployeeDTO.builder().id(employee.getId()).firstName(employee.getFirstName())
				.lastName(employee.getLastName()).email(employee.getEmail()).phoneNumber(employee.getPhoneNumber())
				.hireDate(employee.getHireDate()).salary(employee.getSalary()).build();
	}

	private Employee convertToEmployee(EmployeeDTO employeeDTO) {
		return Employee.builder().id(employeeDTO.getId()).firstName(employeeDTO.getFirstName())
				.lastName(employeeDTO.getLastName()).email(employeeDTO.getEmail())
				.phoneNumber(employeeDTO.getPhoneNumber()).hireDate(employeeDTO.getHireDate())
				.salary(employeeDTO.getSalary()).build();
	}

}
