package com.rushikesh.service;

import java.util.List;

import com.rushikesh.dto.MemberRequestDTO;
import com.rushikesh.dto.MemberResponseDTO;

public interface MemberService {

	public MemberResponseDTO addMember(MemberRequestDTO request);

	public List<MemberResponseDTO> getAllMembers();

	public MemberResponseDTO getMemberById(Integer memberId);

	public MemberResponseDTO updateMember(Integer memberId, MemberRequestDTO memberEntity);

	public String deleteMember(Integer memberId);

}
