package com.rubypaper.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
	
	private Long seq;
	
	@NotBlank(message = "제목")
	@Size(min = 2, max = 100, message = "2 ~ 100")
	private String title;
	
	@NotBlank(message="내용")
	@Size(min = 10, max = 1000, message = "10 ~ 1000")
	private String content;
	
	private Date createDate;
	
	private Long cnt;
	
	private MemberDto memberDto;
	
	public Board toEntity() {
		return Board.builder()
				.title(this.title)
				.content(this.content)
				.member(this.memberDto.toEntity())
				.build();
	}
}
