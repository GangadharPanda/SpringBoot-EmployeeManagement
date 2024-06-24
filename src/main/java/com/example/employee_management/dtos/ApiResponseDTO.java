package com.example.employee_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ApiResponseDTO {
	private String message;
	private Object data;
	private Object error;
}
