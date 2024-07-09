package com.example.employee_management.controllers;

import com.example.employee_management.dtos.ApiResponseDTO;
import com.example.employee_management.dtos.EmployeeDTO;
import com.example.employee_management.dtos.PageInfo;
import com.example.employee_management.entities.Employee;
import com.example.employee_management.services.IEmployeeService;
import com.example.employee_management.services.ISearchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employee/v1")
public class SearchController {

	@Autowired
	private ISearchService iSearchService;

	@PostMapping("/search")
	public Page<Employee> searchEmployee(@RequestBody PageInfo pageInfo) {
		return iSearchService.getEmployeeByName(pageInfo.getQuery(),
				pageInfo.getPageNumber(),
				pageInfo.getPageSize());
	}

}
