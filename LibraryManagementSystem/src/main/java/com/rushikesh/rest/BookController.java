package com.rushikesh.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rushikesh.dto.BookRequestDTO;
import com.rushikesh.dto.BookResponseDTO;
import com.rushikesh.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping
	public BookResponseDTO addBook(@Valid @RequestBody BookRequestDTO book) {

		return bookService.addBook(book);
	}

	@GetMapping("/getBooks")
	public Page<BookResponseDTO> getAllBooks(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "bookId") String sortBy,
			@RequestParam(defaultValue = "ASC") Sort.Direction direction) {
		return bookService.getAllBooks(page, size, sortBy, direction);
	}

	@GetMapping("/{BookId}")
	public BookResponseDTO getBookById(@PathVariable Integer BookId) {
		return bookService.getBookById(BookId);
	}

	@PutMapping("/{BookId}")
	public BookResponseDTO updateBook(@PathVariable Integer BookId, @Valid @RequestBody BookRequestDTO book) {
		return bookService.updateBook(BookId, book);
	}

	@DeleteMapping("/{BookId}")
	public String deleteBookById(@PathVariable Integer BookId) {

		bookService.deleteBook(BookId);

		return "Book Deleted Successfully";
	}
}
