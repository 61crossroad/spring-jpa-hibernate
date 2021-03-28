package com.rubypaper.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString(exclude="member")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {
	@Id
	@GeneratedValue
	private Long seq;
	
	private String title;
	
	private String content;
	
	@Builder.Default
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createDate = new Date();
	
	@Builder.Default
	@Column(updatable = false)
	private Long cnt = 0L;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}
	
	@Builder.Default
	@OneToMany(mappedBy = "board")
	private List<Like> likeList = new ArrayList<Like>();
	
	public BoardDto toDto() {
		return BoardDto.builder()
				.seq(this.seq)
				.title(this.title)
				.content(this.content)
				.createDate(this.createDate)
				.cnt(this.cnt)
				.memberDto(this.member.toDto())
				.build();
	}
}
