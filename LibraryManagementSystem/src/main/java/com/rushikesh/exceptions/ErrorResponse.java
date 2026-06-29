package com.rushikesh.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 
public class ErrorResponse {
 
	private LocalDateTime timsestamp;
	private int status;
	private String message;
}
