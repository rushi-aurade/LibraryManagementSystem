package com.rushikesh.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rushikesh.dto.BookIssueRequestDTO;
import com.rushikesh.dto.BookIssueResponseDTO;
import com.rushikesh.dto.ReturnBookRequestDTO;
import com.rushikesh.service.BookIssueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/issues")
public class BookServiceController {

	@Autowired
	private BookIssueService bookIssueService;

	@PostMapping
	public BookIssueResponseDTO issueBook(@Valid @RequestBody BookIssueRequestDTO issue) {

		return bookIssueService.issueBook(issue);

	}

	@GetMapping
	public List<BookIssueResponseDTO> getAllIssuedBooks() {

		return bookIssueService.getAllIssuedBooks();
	}

	@GetMapping("/{issuedId}")
	public BookIssueResponseDTO getBookIssuedById(@PathVariable Integer issuedId) {

		return bookIssueService.getIssuedById(issuedId);

	}

	@PutMapping("/{issuedId}")
	public BookIssueResponseDTO updateIssueById(@PathVariable Integer issuedId,
			@RequestBody ReturnBookRequestDTO issue) {

		return bookIssueService.updateIssue(issuedId, issue);
	}

	@DeleteMapping("/{issuedId}")
	public String deleteIssuedById(@PathVariable Integer issuedId) {

		bookIssueService.deleteIsssued(issuedId);

		return "Issue Record Deletd Successfully";
	}

}
