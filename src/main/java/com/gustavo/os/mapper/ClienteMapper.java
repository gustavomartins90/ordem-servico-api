package com.gustavo.os.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gustavo.os.domain.model.Cliente;
import com.gustavo.os.domain.model.dto.ClienteDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClienteMapper {
	
	private ModelMapper modelMapper;
	
	public ClienteDTO toDTO(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDTO.class);
	}
	
	public List<ClienteDTO> toListDTO(List<Cliente> listCliente){
		return listCliente.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
}
