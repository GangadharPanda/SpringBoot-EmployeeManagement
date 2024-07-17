package com.example.em.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.em.dtos.PageInfo;
import com.example.em.entities.Employee;
import com.example.em.services.ISearchService;

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
