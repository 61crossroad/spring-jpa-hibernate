package com.rubypaper.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.rubypaper.domain.Board;

public interface DynamicBoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
    /*
    long count(Predicate p)
    boolean exists(Predicate p)
    Iterable<T> findAll(Predicate p)
    Page<T> findAll(Predicate p)
    Iterable<t> findAll(Predicate p, Sort s)
    T findOne(Predicate p)
     */
}
