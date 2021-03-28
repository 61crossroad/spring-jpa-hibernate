package com.rubypaper.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardDto;
import com.rubypaper.service.BoardService;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@RestController
@Validated
public class BoardController {
	private final static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private BoardService boardService;
	
	@GetMapping("/getBoardList")
	public ResponseEntity<List<BoardDto>> getBoardList(BoardDto boardDto) {
		List<BoardDto> boardDtoList = boardService.getBoardList(boardDto);
		
		int boardDtoListSize = boardDtoList.size();
		
		if (0 < boardDtoListSize) {
			return new ResponseEntity<List<BoardDto>>(boardDtoList, HttpStatus.OK);
		} else if (boardDtoListSize == 0) {
			return new ResponseEntity<List<BoardDto>>(boardDtoList, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<BoardDto>>(boardDtoList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/insertBoard")
	public ResponseEntity<BoardDto> insertBoard(@RequestBody @Valid BoardDto boardDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			this.logFieldErrors(bindingResult.getFieldErrors());
			
			return new ResponseEntity<BoardDto>(new BoardDto(), HttpStatus.BAD_REQUEST);
		}
		
		BoardDto insertedBoardDto = boardService.insertBoard(boardDto);
		
		if (insertedBoardDto.getSeq() == null) {
			return new ResponseEntity<BoardDto>(insertedBoardDto, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<BoardDto>(insertedBoardDto, HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/getBoard/{seq}")
	public ResponseEntity<BoardDto> getBoard(@PathVariable(name = "seq", required = false) @Min(0) Long seq) {
		BoardDto selectedBoardDto = boardService.getBoard(seq);
		
		if (selectedBoardDto.getSeq() == null) {
			return new ResponseEntity<BoardDto>(selectedBoardDto, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<BoardDto>(selectedBoardDto, HttpStatus.OK);
		}
	}
	
	@PutMapping("/updateBoard")
	public ResponseEntity<BoardDto> updateBoard(@RequestBody @Valid BoardDto boardDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			this.logFieldErrors(bindingResult.getFieldErrors());
			
			return new ResponseEntity<BoardDto>(new BoardDto(), HttpStatus.BAD_REQUEST);
		}
		
		BoardDto updatedBoardDto = boardService.updateBoard(boardDto);
		
		if (updatedBoardDto.getSeq() == null) {
			return new ResponseEntity<BoardDto>(updatedBoardDto, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<BoardDto>(updatedBoardDto, HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/deleteBoard/{seq}")
	public ResponseEntity<String> deleteBoard(@PathVariable("seq") Long seq) {
		if (boardService.deleteBoard(seq)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private void logFieldErrors(List<FieldError> fieldErrorList) {
		fieldErrorList.forEach(fieldError -> {
			logger.warn("{} : {} > {}", fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage());
		});
	}
}
