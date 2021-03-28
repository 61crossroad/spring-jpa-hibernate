package com.rubypaper.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {
	
	List<Board> findByMember(Member member);
	
	@Query("SELECT b FROM Board b")
	Page<Board> selectBoardList(Pageable pageable);
}
