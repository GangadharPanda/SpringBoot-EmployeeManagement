package com.example.employee_management.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class PageInfo {
	private String query;
	private Integer pageNumber;
	private Integer pageSize;
}
