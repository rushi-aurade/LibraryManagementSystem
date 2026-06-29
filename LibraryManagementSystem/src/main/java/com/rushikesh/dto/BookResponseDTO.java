package com.rushikesh.dto;

import lombok.Data;

@Data
public class BookResponseDTO {
	private Integer bookId;
	private String title;
	private String author;
	private String category;
	private String isbn;
	private Integer quantity;
	private Integer availableQuantity;
}
