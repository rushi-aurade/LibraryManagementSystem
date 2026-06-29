package com.rushikesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rushikesh.entity.BookEntity;
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
	
	

}
