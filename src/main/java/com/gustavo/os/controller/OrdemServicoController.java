package com.gustavo.os.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavo.os.domain.model.OrdemServico;
import com.gustavo.os.domain.model.dto.OrdemServicoDTO;
import com.gustavo.os.mapper.OrdemServicoMapper;
import com.gustavo.os.service.OrdemServicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/ordem-servico")
public class OrdemServicoController {
	
	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@Autowired
	private OrdemServicoMapper ordemServicoMapper;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Long id) {
		OrdemServico obj = this.ordemServicoService.findById(id);
		return ResponseEntity.ok().body(this.ordemServicoMapper.toDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<OrdemServicoDTO>> findAll() {
		List<OrdemServico> listOrdemServico = this.ordemServicoService.findAll();
		return ResponseEntity.ok().body(this.ordemServicoMapper.toListDTO(listOrdemServico));
	}
	
	@PostMapping
	public ResponseEntity<OrdemServicoDTO> create(@Valid @RequestBody OrdemServico obj) {
		obj = this.ordemServicoService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(this.ordemServicoMapper.toDTO(obj));
	}
	
	@PutMapping
	public ResponseEntity<OrdemServicoDTO> update(@Valid @RequestBody OrdemServico obj) {
		obj = this.ordemServicoService.update(obj);
		return ResponseEntity.ok().body(this.ordemServicoMapper.toDTO(obj));
	}

}
