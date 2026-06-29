package com.rushikesh.exceptions;

public class BookIsAlreadyReturnedException extends RuntimeException {

	public BookIsAlreadyReturnedException(String message) {

		super(message);
	}

}
