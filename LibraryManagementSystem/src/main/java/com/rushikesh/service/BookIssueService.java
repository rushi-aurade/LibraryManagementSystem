package com.rushikesh.service;

import java.util.List;

import com.rushikesh.dto.BookIssueRequestDTO;
import com.rushikesh.dto.BookIssueResponseDTO;
import com.rushikesh.dto.ReturnBookRequestDTO;

public interface BookIssueService {

	public BookIssueResponseDTO issueBook(BookIssueRequestDTO issue);

	List<BookIssueResponseDTO> getAllIssuedBooks();

	BookIssueResponseDTO getIssuedById(Integer IssuedId);

	BookIssueResponseDTO updateIssue(Integer issuedId, ReturnBookRequestDTO issue);

	void deleteIsssued(Integer IssuedId);

	BookIssueResponseDTO returnBook(Integer  issuedId);
}
