package com.rubypaper.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

@Builder
@ToString(exclude="boardList")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
	@Id
	@Column(name = "MEMBER_ID")
	private String id;
	
	private String password;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean enabled;
	
	@Builder.Default
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER)
	private List<Board> boardList = new ArrayList<Board>();
	
	@Builder.Default
	@OneToMany(mappedBy="member")
	private List<Like> likeList = new ArrayList<Like>();
	
	public MemberDto toDto() {
		return MemberDto.builder()
				.id(this.id)
				.password(this.password)
				.name(this.name)
				.role(this.role)
				.enabled(this.enabled)
				.build();
	}
}
