package com.gustavo.os.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gustavo.os.domain.model.OrdemServico;
import com.gustavo.os.domain.model.dto.OrdemServicoDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OrdemServicoMapper {
	
	private ModelMapper modelMapper;
	
	public OrdemServicoDTO toDTO(OrdemServico os) {
		return modelMapper.map(os, OrdemServicoDTO.class);
	}
	
	public List<OrdemServicoDTO> toListDTO(List<OrdemServico> listOrdemServico){
		return listOrdemServico.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
}
