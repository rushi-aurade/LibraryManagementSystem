package com.rushikesh.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookIssueResponseDTO {
	
	private Integer issuedId;

	private LocalDate issueDate;

	private LocalDate returnDate;

	private String status;

	private Integer bookId;

	private String bookTitle;

	private Integer memberId;

	private String memberName;

}
