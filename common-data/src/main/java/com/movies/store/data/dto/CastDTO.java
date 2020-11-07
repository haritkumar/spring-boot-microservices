package com.movies.store.data.dto;

import java.util.ArrayList;
import java.util.List;

public class CastDTO {

	private String id;

	private String name;

	private List<MoviesDTO> movies = new ArrayList<MoviesDTO>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MoviesDTO> getMovies() {
		return movies;
	}

	public void setMovies(List<MoviesDTO> movies) {
		this.movies = movies;
	}

}
