package com.rubypaper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import com.rubypaper.domain.BoardVO;
import com.rubypaper.service.BoardService;

// @WebMvcTest
@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
// @SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BoardControllerTest {
	@Autowired
	private MockMvc mockMvc;
	// @Autowired
	// private TestRestTemplate restTemplate;
	@MockBean
	private BoardService boardService;
	
	@Test
	public void testHello() throws Exception {
		/*
		mockMvc.perform(get("/hello").param("name", "둘리"))
		.andExpect(status().isOk())
		.andExpect(content().string("Hello : 둘리"))
		.andDo(print());
		*/
		
		/*
		String result = restTemplate.getForObject("/hello?name=둘리", String.class);
		assertEquals("Hello : 둘리", result);
		*/
		
		when(boardService.hello("둘리")).thenReturn("Hello : 둘리");
		
		mockMvc.perform(get("/hello").param("name", "둘리"))
		.andExpect(status().isOk())
		.andExpect(content().string("Hello : 둘리"))
		.andDo(print());
	}
	
	// @Test
	public void testGetBoard() throws Exception {
		/*
		BoardVO board = restTemplate.getForObject("/getBoard", BoardVO.class);
		assertEquals("테스터", board.getWriter());
		*/
	}
}
