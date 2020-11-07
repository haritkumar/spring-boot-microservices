package com.movies.store.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.store.data.dto.CastDTO;
import com.movies.store.request.CreateCast;
import com.movies.store.request.UpdateCast;
import com.movies.store.service.ICastService;

import javassist.NotFoundException;

@RestController
@RequestMapping(path = "/cast")
@CrossOrigin(origins = {"http://"+"${gateway.host}"})
public class Api {

	@Autowired
	private ICastService service;
	
	/**
	 * Save a cast
	 * 
	 * @param castRequest
	 * @return {@link ResponseEntity}
	 */
	@PostMapping
	public ResponseEntity<CastDTO> insert(@Valid @RequestBody CreateCast createCast) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		CastDTO castDTO = modelMapper.map(createCast, CastDTO.class);
		
		castDTO = service.insert(castDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(castDTO);
	}

	/**
	 * Return a cast by id
	 * 
	 * @param castId
	 * @return {@link ResponseEntity}
	 * @throws NotFoundException 
	 */
	@GetMapping(value = "/{castId}")
	public ResponseEntity<CastDTO> getById(@PathVariable("castId") String castId) throws NotFoundException {
		CastDTO castDTO = service.findById(castId);
		return (castDTO != null) ? ResponseEntity.status(HttpStatus.OK).body(castDTO) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	/**
	 * Return all casts
	 * 
	 * @param castId
	 * @return {@link ResponseEntity}
	 */
	@GetMapping
	public ResponseEntity<List<CastDTO>> getAll() {
		List<CastDTO> casts = service.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(casts);
	}

	/**
	 * Update a cast
	 * @return
	 * @throws NotFoundException 
	 */
	@PutMapping
	public ResponseEntity<CastDTO> update(@Valid @RequestBody UpdateCast updateCast) {
		if(service.findById(updateCast.getId()) == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		CastDTO castDTO = modelMapper.map(updateCast, CastDTO.class);
		
		castDTO = service.update(castDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(castDTO);
	}

	/**
	 * Delete a cast
	 * @return
	 * @throws NotFoundException 
	 */
	@DeleteMapping(path = "/{castId}")
	public ResponseEntity<Void> delete(@PathVariable String castId) {
		if(service.findById(castId) == null)
			return ResponseEntity.notFound().build();
		
		service.delete(castId);
		return ResponseEntity.ok().build();
	}
}
