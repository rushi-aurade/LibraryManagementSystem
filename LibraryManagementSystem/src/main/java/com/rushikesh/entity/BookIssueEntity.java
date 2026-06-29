package com.rushikesh.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "book_issued")
public class BookIssueEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer issuedId;

	private LocalDate issueDate;

	private LocalDate returnDate;

	private String status;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private BookEntity book;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private MemberEntity member;

}
