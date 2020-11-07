package com.movies.store.service;

import java.util.List;

import com.movies.store.data.dto.CastDTO;

public interface ICastService {

	public CastDTO insert(CastDTO category);

	public CastDTO update(CastDTO category);

	public List<CastDTO> getAll();

	public CastDTO findById(String id);

	public void delete(String id);
}
