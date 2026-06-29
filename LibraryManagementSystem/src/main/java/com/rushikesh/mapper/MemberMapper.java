package com.rushikesh.mapper;

import com.rushikesh.dto.MemberRequestDTO;
import com.rushikesh.dto.MemberResponseDTO;
import com.rushikesh.entity.MemberEntity;

public class MemberMapper {

	public static MemberEntity toEntity(MemberRequestDTO request) {

		MemberEntity member = new MemberEntity();
		member.setMemberEmail(request.getMemberEmail());
		member.setMemberName(request.getMemberName());
		member.setMemberPhoneNo(request.getMemberPhoneNo());
		return member;
	}

	public static MemberResponseDTO toResponse(MemberEntity member) {

		MemberResponseDTO response = new MemberResponseDTO();
		 response.setMemberEmail(member.getMemberEmail());
		 response.setMemberId(member.getMemberId());
		 response.setMemberName(member.getMemberName());
		 response.setMemberPhoneNo(member.getMemberPhoneNo());
		return response;

	}
}
