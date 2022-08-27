package com.gustavo.os.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.os.domain.model.OrdemServico;
import com.gustavo.os.domain.repository.OrdemServicoRepository;
import com.gustavo.os.exception.ObjectNotFoundException;

@Service
public class OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OrdemServico findById(Long id) {
		return this.ordemServicoRepository.findById(id).orElseThrow(()-> 
			new ObjectNotFoundException("Objeto não encontrado! ID: "+id+" Classe: "+OrdemServico.class.getSimpleName()));
	}
	
	public List<OrdemServico> findAll() {
		return this.ordemServicoRepository.findAll();
	}
	
	public OrdemServico create(OrdemServico obj) {
		obj.setTecnico(this.tecnicoService.findById(obj.getTecnico().getId()));
		obj.setCliente(this.clienteService.findById(obj.getCliente().getId()));
		obj.setId(null);
		return this.ordemServicoRepository.save(obj);
	}

	public OrdemServico update(OrdemServico obj) {
		this.findById(obj.getId());
		obj.setTecnico(this.tecnicoService.findById(obj.getTecnico().getId()));
		obj.setCliente(this.clienteService.findById(obj.getCliente().getId()));

		if(obj.getStatus().getCod().equals(2)) {
			obj.setDataFechamento(new Date());
		}else {
			obj.setDataFechamento(null);
		}
		
		return this.ordemServicoRepository.save(obj);
	}
	
}
