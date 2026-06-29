package com.rushikesh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushikesh.dto.MemberRequestDTO;
import com.rushikesh.dto.MemberResponseDTO;
import com.rushikesh.entity.MemberEntity;
import com.rushikesh.mapper.MemberMapper;
import com.rushikesh.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepo;

	@Override
	public MemberResponseDTO addMember(MemberRequestDTO request) {

		MemberEntity entity = MemberMapper.toEntity(request);
		MemberEntity save = memberRepo.save(entity);
		return MemberMapper.toResponse(save);
	}

	@Override
	public List<MemberResponseDTO> getAllMembers() {

		List<MemberEntity> allMembers = memberRepo.findAll();
		return allMembers.stream().map(MemberMapper::toResponse).toList();
	}

	@Override
	public MemberResponseDTO getMemberById(Integer memberId) {

		MemberEntity member = memberRepo.findById(memberId)
				.orElseThrow(() -> new RuntimeException("Member Not Found With id" + memberId));

		return MemberMapper.toResponse(member);

	}

	@Override
	public MemberResponseDTO updateMember(Integer memberId, MemberRequestDTO member) {

		MemberEntity existingMember = memberRepo.findById(memberId)
				.orElseThrow(() -> new RuntimeException("Book Not Available With Id" + memberId));

		existingMember.setMemberEmail(member.getMemberEmail());
		existingMember.setMemberName(member.getMemberName());
		existingMember.setMemberPhoneNo(member.getMemberPhoneNo());

		MemberEntity save = memberRepo.save(existingMember);
		return MemberMapper.toResponse(save);

	}

	@Override
	public String deleteMember(Integer memberId) {

		memberRepo.deleteById(memberId);

		return "Member Deleted Sucessfully";

	}

}
