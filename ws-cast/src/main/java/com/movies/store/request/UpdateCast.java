package com.movies.store.request;

import javax.validation.constraints.NotNull;

public class UpdateCast extends CreateCast{

	@NotNull(message = "id cannot be null")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
