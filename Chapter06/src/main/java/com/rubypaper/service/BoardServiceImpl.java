package com.rubypaper.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.BoardDto;
import com.rubypaper.persistence.BoardRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	private final static Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	private BoardRepository boardRepo;
	
	public List<BoardDto> getBoardList(BoardDto boardDto) {
		List<Board> boardList = (List<Board>) boardRepo.findAll();
		
		List<BoardDto> boardDtoList = new ArrayList<BoardDto>();
		boardList.forEach(board -> {
			boardDtoList.add(board.toDto());
		});
		return boardDtoList;
	}
	
	public BoardDto insertBoard(BoardDto boardDto) {
		Board board = boardDto.toEntity();
		
		return boardRepo.save(board).toDto();
	}
	
	public BoardDto getBoard(Long seq) {
		BoardDto boardDto = new BoardDto();
		
		seq = (seq == null) ? 1 : seq;
		boardDto.setSeq(seq);
		
		return this.getBoard(boardDto);
	}
	
	public BoardDto getBoard(BoardDto boardDto) {
		Optional<Board> optionalBoard = boardRepo.findById(boardDto.getSeq());
		
		if (optionalBoard.isPresent()) {
			return optionalBoard.get().toDto();
		} else {
			return new BoardDto();
		}
	}
	
	public BoardDto updateBoard(BoardDto boardDto) {
		Optional<Board> optionalBoard = boardRepo.findById(boardDto.getSeq());
		
		if (optionalBoard.isPresent()) {
			Board board = optionalBoard.get();
			board.setTitle(boardDto.getTitle());
			board.setContent(boardDto.getContent());
			
			return boardRepo.save(board).toDto();
		} else {
			return new BoardDto();
		}
	}
	
	public boolean deleteBoard(Long seq) {
		try {
			boardRepo.deleteById(seq);
			logger.info("deleteBoard #{} succeed!", seq);
			
			return true;
		} catch (IllegalArgumentException e) {
			logger.warn("deleteBoard #{} failed for ", seq, e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}
}
