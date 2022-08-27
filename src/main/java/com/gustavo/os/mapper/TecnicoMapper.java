package com.gustavo.os.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gustavo.os.domain.model.Tecnico;
import com.gustavo.os.domain.model.dto.TecnicoDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TecnicoMapper {
	
	private ModelMapper modelMapper;
	
	public TecnicoDTO toDTO(Tecnico tecnico) {
		return modelMapper.map(tecnico, TecnicoDTO.class);
	}
	
	public List<TecnicoDTO> toListDTO(List<Tecnico> listTecnico){
		return listTecnico.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
}
