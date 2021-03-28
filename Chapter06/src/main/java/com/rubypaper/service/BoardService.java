package com.rubypaper.service;

import java.util.List;

import com.rubypaper.domain.BoardDto;

public interface BoardService {

	List<BoardDto> getBoardList(BoardDto boardDto);

	BoardDto insertBoard(BoardDto boardDto);

	BoardDto getBoard(Long seq);
	
	BoardDto getBoard(BoardDto boardDto);

	BoardDto updateBoard(BoardDto boardDto);

	boolean deleteBoard(Long seq);

}