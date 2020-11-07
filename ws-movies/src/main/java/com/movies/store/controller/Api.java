package com.movies.store.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class Api {

	@Value("${config.test}")
	private String configTest;
	
	@GetMapping
	public String getMovie() {
		return configTest;
	}
}
