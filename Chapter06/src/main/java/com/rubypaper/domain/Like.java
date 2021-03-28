package com.rubypaper.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
public class Like {
	@Id
	@GeneratedValue
	private Long seq;
	
	@ManyToOne
	@JoinColumn(name = "SEQ", nullable = false)
	private Board board;
	
	public void setBoard(Board board) {
		this.board = board;
		board.getLikeList().add(this);
	}
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getLikeList().add(this);
	}
}
