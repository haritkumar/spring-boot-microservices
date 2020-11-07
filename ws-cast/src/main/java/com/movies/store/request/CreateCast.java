package com.movies.store.request;

import javax.validation.constraints.NotNull;

public class CreateCast {

	@NotNull(message = "name cannot be null")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
