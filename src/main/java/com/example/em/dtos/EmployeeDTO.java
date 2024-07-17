package com.example.em.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class EmployeeDTO {

	private Long id;

	@NotBlank(message = "First name is mandatory")
	private String firstName;

	private String lastName;

	@NotBlank(message = "Email is mandatory")
	private String email;

	private String phoneNumber;

	private LocalDate hireDate;

	private BigDecimal salary;

}
