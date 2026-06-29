package com.rushikesh.mapper;

import com.rushikesh.dto.BookIssueRequestDTO;
import com.rushikesh.dto.BookIssueResponseDTO;
import com.rushikesh.entity.BookIssueEntity;

public class BookIssueMapper {

	public static BookIssueEntity toEntity(BookIssueRequestDTO request) {

		BookIssueEntity bookIssue = new BookIssueEntity();

		return bookIssue;
	}

	public static BookIssueResponseDTO toResponse(BookIssueEntity entity) {

		BookIssueResponseDTO response = new BookIssueResponseDTO();
		response.setIssueDate(entity.getIssueDate());
		response.setIssuedId(entity.getIssuedId());;
		response.setReturnDate(entity.getReturnDate());
		response.setStatus(entity.getStatus());

		response.setBookId(entity.getBook().getBookId());
		response.setBookTitle(entity.getBook().getTitle());

		response.setMemberId(entity.getMember().getMemberId());
		response.setMemberName(entity.getMember().getMemberName());

		return response;

	}

}
