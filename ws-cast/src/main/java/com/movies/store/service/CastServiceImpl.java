package com.movies.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.store.data.dto.CastDTO;
import com.movies.store.data.entity.Cast;
import com.movies.store.repo.ICastRepository;

@Service("castService")
public class CastServiceImpl implements ICastService {

	@Autowired
	private ICastRepository repository;

	@Override
	public CastDTO insert(CastDTO cast) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Cast Cast = modelMapper.map(cast, Cast.class);
		Cast.setId(UUID.randomUUID().toString());
		
		Cast = repository.save(Cast);
		cast = modelMapper.map(Cast, CastDTO.class);
		
		return cast;
	}
	
	@Override
	public CastDTO update(CastDTO cast) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Cast Cast = modelMapper.map(cast, Cast.class);
		
		Cast = repository.save(Cast);
		cast = modelMapper.map(Cast, CastDTO.class);
		
		return cast;
	}

	@Override
	public CastDTO findById(String id) {
		Cast cast = repository.findById(id).orElse(null);
		
		if(cast == null)
			return null;
		
		CastDTO CastDTO = new ModelMapper().map(cast, CastDTO.class);
		
		return CastDTO;
	}
	
	@Override
	public List<CastDTO> getAll() {
		ArrayList<Cast> casts = (ArrayList<Cast>) repository.findAll();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<CastDTO> castsDTO = casts
				  .stream()
				  .map(cast -> modelMapper.map(cast, CastDTO.class))
				  .collect(Collectors.toList());
		
		return castsDTO;
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

}
