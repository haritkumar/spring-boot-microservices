package com.movies.store.repo;

import org.springframework.data.repository.CrudRepository;

import com.movies.store.data.entity.Cast;

public interface ICastRepository extends CrudRepository<Cast, String>{

}
