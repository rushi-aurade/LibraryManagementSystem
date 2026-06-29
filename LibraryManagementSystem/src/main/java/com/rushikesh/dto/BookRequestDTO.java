package com.rushikesh.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookRequestDTO {

	@NotBlank(message = "Title is required")
	@Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
	private String title;

	@NotBlank(message = "Author is required")
	private String author;

	@NotBlank(message = "Category is required")
	private String category;

	@NotBlank(message = "ISBN is required")
	@Column(unique = true)
	private String isbn;

	@NotNull(message = "Quantity is required")
	@Min(value = 0, message = "Quantity cannot be negative")
	private Integer quantity;

}
