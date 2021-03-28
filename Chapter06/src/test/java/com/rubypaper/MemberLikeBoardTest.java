package com.rubypaper;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.LikeRepository;
import com.rubypaper.persistence.MemberRepository;

@SpringBootTest
public class MemberLikeBoardTest {
	private final static Logger logger = LoggerFactory.getLogger(MemberLikeBoardTest.class);
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private LikeRepository likeRepo;
	
	@BeforeEach
	public void prepareData() {
		Member member1 = new Member();
		member1.setId("member");
		member1.setPassword("member123");
		member1.setName("둘리");
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled(true);
		memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setId("admin");
		member2.setPassword("admin123");
		member2.setName("도우너");
		member2.setRole(Role.ROLE_ADMIN);
		member2.setEnabled(true);
		memberRepo.save(member2);
		
		for (int i = 1; i <= 23; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle(member1.getName() + "가 등록한 게시글 " + i);
			board.setContent(member1.getName() + "가 등록한 게시글 내용 " + i);
			boardRepo.save(board);
		}
		
		for (int i = 1; i <= 13; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle(member2.getName() + "가 등록한 게시글 " + i);
			board.setContent(member2.getName() + "가 등록한 게시글 내용 " + i);
			boardRepo.save(board);
		}
	}
	
	@Test
	public void insertLike() {
		Member member = memberRepo.findById("member").get();
		List<Board> boardList = boardRepo.findByMember(member);
		// boardList.forEach(action);
	}
}
