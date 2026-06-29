package com.rushikesh.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.rushikesh.dto.BookRequestDTO;
import com.rushikesh.dto.BookResponseDTO;

public interface BookService {

	public BookResponseDTO addBook(BookRequestDTO book);

	public Page<BookResponseDTO> getAllBooks(Integer page, Integer size, String sortBy, Sort.Direction direction);

	public BookResponseDTO getBookById(Integer BookId);

	public BookResponseDTO updateBook(Integer BookId, BookRequestDTO book);

	public void deleteBook(Integer BookId);

}
