package com.rushikesh.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReturnBookRequestDTO {

	private String status;
	
	private LocalDate returnDate;
}
