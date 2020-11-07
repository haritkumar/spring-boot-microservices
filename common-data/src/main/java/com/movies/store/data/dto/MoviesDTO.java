package com.movies.store.data.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class MoviesDTO {

	private String id;

	private String title;

	@JsonBackReference
	private CastDTO cast;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CastDTO getCast() {
		return cast;
	}

	public void setCast(CastDTO cast) {
		this.cast = cast;
	}
	
	
}
