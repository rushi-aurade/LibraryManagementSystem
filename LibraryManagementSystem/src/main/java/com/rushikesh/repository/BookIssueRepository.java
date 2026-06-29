package com.rushikesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rushikesh.entity.BookIssueEntity;

public interface BookIssueRepository extends JpaRepository<BookIssueEntity, Integer> {

}
