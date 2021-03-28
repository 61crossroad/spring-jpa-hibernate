package com.rubypaper.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.MemberDto;
import com.rubypaper.service.MemberService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class MemberController {
	private MemberService memberService;
	
	@PostMapping("/login")
	public ResponseEntity<MemberDto> login(@RequestBody @Valid MemberDto memberDto) {
		MemberDto findMemberDto = memberService.getMember(memberDto);
		
		if (findMemberDto != null && findMemberDto.getPassword().equals(memberDto.getPassword())) {
			return new ResponseEntity<MemberDto>(findMemberDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<MemberDto>(new MemberDto(), HttpStatus.BAD_REQUEST);
		}
	}
}
