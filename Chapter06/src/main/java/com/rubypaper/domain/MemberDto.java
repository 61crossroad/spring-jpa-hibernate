package com.rubypaper.domain;

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
public class MemberDto {
	@NotBlank(message = "아이디")
	@Size(min = 3, max = 20, message = "3 ~ 20")
	private String id;
	
	@NotBlank(message = "비밀번호")
	@Size(min = 4, max = 30, message = "4 ~ 30")
	private String password;
	
	@Size(min = 2, max = 20, message = "2 ~ 20")
	private String name;
	
	private Role role;
	
	private boolean enabled;
	
	public Member toEntity() {
		return Member.builder()
				.id(this.id)
				.password(this.password)
				.name(this.name)
				.role(this.role)
				.enabled(this.enabled)
				.build();
	}
}
