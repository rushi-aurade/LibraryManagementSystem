package com.rushikesh.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberRequestDTO {

	@NotBlank(message = "Member Name Is Required")
	@Size(min = 5, max = 50)
	private String memberName;

	@NotBlank(message = "Email Is Required")
	@Email(message = "Enter Valid Mail")
	@Column(unique = true)
	private String memberEmail;

	@NotBlank(message = "Phone No Is Required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	private String memberPhoneNo;

}
