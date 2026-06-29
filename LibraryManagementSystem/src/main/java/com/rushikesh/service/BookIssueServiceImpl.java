package com.rushikesh.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushikesh.dto.BookIssueRequestDTO;
import com.rushikesh.dto.BookIssueResponseDTO;
import com.rushikesh.dto.ReturnBookRequestDTO;
import com.rushikesh.entity.BookEntity;
import com.rushikesh.entity.BookIssueEntity;
import com.rushikesh.entity.MemberEntity;
import com.rushikesh.exceptions.BookIsAlreadyReturnedException;
import com.rushikesh.exceptions.BookNotAvailableException;
import com.rushikesh.exceptions.BookNotFoundException;
import com.rushikesh.exceptions.IssueRecordNotFoundException;
import com.rushikesh.exceptions.MemberNotFoundException;
import com.rushikesh.mapper.BookIssueMapper;
import com.rushikesh.repository.BookIssueRepository;
import com.rushikesh.repository.BookRepository;
import com.rushikesh.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookIssueServiceImpl implements BookIssueService {

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private BookIssueRepository bookIssueRepo;

	@Autowired
	private MemberRepository memberRepo;

	@Override
	public BookIssueResponseDTO issueBook(BookIssueRequestDTO request) {
		BookEntity book = bookRepo.findById(request.getBookId())
				.orElseThrow(() -> new BookNotFoundException("Book Not Found"));

		MemberEntity member = memberRepo.findById(request.getMemberId())
				.orElseThrow(() -> new MemberNotFoundException("Member Not Found"));

		if (book.getAvailableQuantity() <= 0) {

			throw new BookNotAvailableException("No Books Available");
		}

		book.setAvailableQuantity(book.getAvailableQuantity() - 1);
		bookRepo.save(book);

		BookIssueEntity issue = BookIssueMapper.toEntity(request);

		issue.setBook(book);
		issue.setMember(member);
		issue.setIssueDate(LocalDate.now());
		issue.setReturnDate(LocalDate.now().plusDays(14));
		issue.setStatus("ISSUED");

		BookIssueEntity savedIssue = bookIssueRepo.save(issue);

		return BookIssueMapper.toResponse(savedIssue);
	}

	@Override
	public List<BookIssueResponseDTO> getAllIssuedBooks() {

		List<BookIssueEntity> allIssuedBooks = bookIssueRepo.findAll();
		return allIssuedBooks.stream().map(BookIssueMapper::toResponse).toList();
	}

	@Override
	public BookIssueResponseDTO getIssuedById(Integer IssuedId) {
		BookIssueEntity bookIssued = bookIssueRepo.findById(IssuedId)
				.orElseThrow(() -> new IssueRecordNotFoundException("Issue Record Not Found"));
		return BookIssueMapper.toResponse(bookIssued);
	}

	@Override
	public BookIssueResponseDTO updateIssue(Integer issuedId, ReturnBookRequestDTO request) {

		BookIssueEntity existingIssue = bookIssueRepo.findById(issuedId)
				.orElseThrow(() -> new IssueRecordNotFoundException("ssue Record Not Found"));

		if ("RETURNED".equalsIgnoreCase(existingIssue.getStatus())) {
			throw new BookIsAlreadyReturnedException("Book Is Already Returned");
		}

		BookIssueEntity updatedIssue = null;
		if (request.getStatus() == null && "RETURNED".equalsIgnoreCase(request.getStatus())) {

			BookEntity book = existingIssue.getBook();

			book.setAvailableQuantity(book.getAvailableQuantity() + 1);
			bookRepo.save(book);

			existingIssue.setStatus("RETURNED");
			existingIssue.setReturnDate(LocalDate.now());

			updatedIssue = bookIssueRepo.save(existingIssue);

		} else {
			existingIssue.setStatus(request.getStatus());
			existingIssue.setReturnDate(request.getReturnDate());
			updatedIssue = bookIssueRepo.save(existingIssue);
		}

		return BookIssueMapper.toResponse(updatedIssue);
	}

	@Override
	public void deleteIsssued(Integer IssuedId) {

		BookIssueEntity issue = bookIssueRepo.findById(IssuedId)
				.orElseThrow(() -> new IssueRecordNotFoundException("Issue Record Not Found"));

		bookIssueRepo.delete(issue);

	}

	@Override
	public BookIssueResponseDTO returnBook(Integer issuedId) {

		BookIssueEntity issue = bookIssueRepo.findById(issuedId)
				.orElseThrow(() -> new IssueRecordNotFoundException("Issued Record Not Found"));

		if ("RETURNED".equalsIgnoreCase(issue.getStatus())) {
			throw new BookIsAlreadyReturnedException("Book is already Returned");
		}

		BookEntity book = issue.getBook();

		if (book == null) {
			throw new BookNotFoundException("Book not found");
		}

		book.setAvailableQuantity(book.getAvailableQuantity() + 1);
		issue.setStatus("RETURNED");
		issue.setReturnDate(LocalDate.now());

		bookRepo.save(book);

		BookIssueEntity save = bookIssueRepo.save(issue);
		return BookIssueMapper.toResponse(save);
	}

}
