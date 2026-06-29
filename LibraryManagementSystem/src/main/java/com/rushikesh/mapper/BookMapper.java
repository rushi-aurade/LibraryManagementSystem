package com.rushikesh.mapper;

import com.rushikesh.dto.BookRequestDTO;
import com.rushikesh.dto.BookResponseDTO;
import com.rushikesh.entity.BookEntity;

public class BookMapper {

	public static BookEntity toEntity(BookRequestDTO request) {

		BookEntity book = new BookEntity();
		book.setTitle(request.getTitle());
		book.setAuthor(request.getAuthor());
		book.setCategory(request.getCategory());
		book.setIsbn(request.getIsbn());
		book.setQuantity(request.getQuantity());

		return book;
	}

	public static BookResponseDTO toResponse(BookEntity entity) {

		BookResponseDTO response = new BookResponseDTO();

		response.setAuthor(entity.getAuthor());
		response.setTitle(entity.getTitle());
		response.setCategory(entity.getCategory());
		response.setIsbn(entity.getIsbn());
		response.setQuantity(entity.getQuantity());
		response.setAvailableQuantity(entity.getAvailableQuantity());
		response.setBookId(entity.getBookId());
		return response;
	}

}
