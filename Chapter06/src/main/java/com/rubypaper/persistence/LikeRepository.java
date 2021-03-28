package com.rubypaper.persistence;

import org.springframework.data.repository.CrudRepository;

import com.rubypaper.domain.Like;

public interface LikeRepository extends CrudRepository<Like, Long> {

}
