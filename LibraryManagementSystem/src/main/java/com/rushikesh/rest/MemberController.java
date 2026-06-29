package com.rushikesh.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rushikesh.dto.MemberRequestDTO;
import com.rushikesh.dto.MemberResponseDTO;
import com.rushikesh.entity.MemberEntity;
import com.rushikesh.service.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/members")
@Validated
public class MemberController {

	@Autowired
	private MemberService memberService;

	@PostMapping
	public MemberResponseDTO saveMember(@Valid @RequestBody MemberRequestDTO memberEntity) {

		return memberService.addMember(memberEntity);

	}

	@GetMapping("/getMembers")
	public List<MemberResponseDTO> getAllMembers() {
		return memberService.getAllMembers();
	}

	@GetMapping("/{memberId}")
	public MemberResponseDTO getMemberById(@PathVariable Integer memberId) {
		return memberService.getMemberById(memberId);
	}

	@PutMapping("/{memberId}")
	public MemberResponseDTO uodateMember(@PathVariable Integer memberId,
			@Valid @RequestBody MemberRequestDTO memberEntity) {

		return memberService.updateMember(memberId, memberEntity);
	}

	@DeleteMapping("/{memberId}")
	public void deleteMember(@PathVariable Integer memberId) {
		memberService.deleteMember(memberId);
	}

}
