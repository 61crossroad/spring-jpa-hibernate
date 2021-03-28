package com.rubypaper.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.MemberDto;
import com.rubypaper.persistence.MemberRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	private MemberRepository memberRepo;
	
	public MemberDto getMember(MemberDto memberDto) {
		Optional<Member> findMember = memberRepo.findById(memberDto.getId());
		
		if (findMember.isPresent()) {
			return findMember.get().toDto();
		} else {
			return new MemberDto();
		}
	}
}
