package com.rushikesh.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rushikesh.dto.BookRequestDTO;
import com.rushikesh.dto.BookResponseDTO;
import com.rushikesh.entity.BookEntity;
import com.rushikesh.exceptions.BookNotAvailableException;
import com.rushikesh.exceptions.BookNotFoundException;
import com.rushikesh.exceptions.InvalidSortFieldException;
import com.rushikesh.mapper.BookMapper;
import com.rushikesh.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepo;
	private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("bookId", "author", "availableQuantity", "category",
			"isbn", "quantity", "title");

	@Override
	public BookResponseDTO addBook(BookRequestDTO request) {

		BookEntity book = BookMapper.toEntity(request);
		book.setAvailableQuantity(book.getQuantity());
		BookEntity savedBook = bookRepo.save(book);

		return BookMapper.toResponse(savedBook);
	}

	@Override
	public Page<BookResponseDTO> getAllBooks(Integer page, Integer size, String sortBy, Sort.Direction direction) {

		if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {

			throw new InvalidSortFieldException("Illegal sort Field");
		}
		Sort sort = Sort.by(direction, sortBy);

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<BookEntity> allBooks = bookRepo.findAll(pageable);

		Page<BookResponseDTO> response = allBooks.map(BookMapper::toResponse);

		return response;

	}

	@Override
	public BookResponseDTO getBookById(Integer BookId) {
		BookEntity existingBook = bookRepo.findById(BookId)
				.orElseThrow(() -> new BookNotFoundException("Book Not Found With Id : " + BookId));
		return BookMapper.toResponse(existingBook);

	}

	@Override
	public BookResponseDTO updateBook(Integer BookId, BookRequestDTO book) {
		BookEntity existingBook = bookRepo.findById(BookId)
				.orElseThrow(() -> new BookNotAvailableException("Book Not Available With Id :" + BookId));

		int issuedBooks = existingBook.getQuantity() - existingBook.getAvailableQuantity();

		if (book.getQuantity() < issuedBooks) {
			throw new IllegalArgumentException("Quantity cannot be less than issued books.");
		}
		existingBook.setTitle(book.getTitle());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setCategory(book.getCategory());
		existingBook.setIsbn(book.getIsbn());
		existingBook.setQuantity(book.getQuantity());

		existingBook.setQuantity(book.getQuantity());
		existingBook.setAvailableQuantity(book.getQuantity() - issuedBooks);
		BookEntity saveBook = bookRepo.save(existingBook);

		return BookMapper.toResponse(saveBook);

	}

	@Override
	public void deleteBook(Integer BookId) {

		bookRepo.deleteById(BookId);

	}

}
