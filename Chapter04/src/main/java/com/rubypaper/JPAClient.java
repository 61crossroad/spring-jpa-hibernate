package com.rubypaper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		/*
		try {
			tx.begin();
			
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요.");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			em.persist(board);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		*/
		/*
		try {
			Board searchBoard = em.find(Board.class, 1L);
			System.out.println("---> " + searchBoard.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		*/
		
		/*
		try {
			tx.begin();
			
			Board board = em.find(Board.class, 1L);
			board.setTitle("검색한 게시글의 제목 수정");
			
			Board board1 = em.find(Board.class, 1L);
			board1.setSeq(1L);
			
			board1.setSeq(1L);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		*/
		
		/*
		try {
			tx.begin();
			
			Board board1 = em.find(Board.class, 1L);
			board1.setSeq(1L);
			
			board1.setSeq(1L);
			em.remove(board1);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		*/
		
		try {
			tx.begin();
			
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요.");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			em.persist(board);
			tx.commit();
			
			String jpql = "select b from Board b order by b.seq desc";
			List<Board> boardList = em.createQuery(jpql, Board.class).getResultList();
			for (Board brd : boardList) {
				System.out.println("---> " + brd.toString());
			}
			// tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
