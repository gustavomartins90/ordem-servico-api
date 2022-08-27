package com.gustavo.os.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavo.os.domain.model.Tecnico;
import com.gustavo.os.domain.model.dto.TecnicoDTO;
import com.gustavo.os.mapper.TecnicoMapper;
import com.gustavo.os.service.TecnicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private TecnicoMapper tecnicoMapper;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Long id) {
		Tecnico obj = this.tecnicoService.findById(id);
		return ResponseEntity.ok().body(this.tecnicoMapper.toDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<Tecnico> listTecnico = this.tecnicoService.findAll();
		return ResponseEntity.ok().body(this.tecnicoMapper.toListDTO(listTecnico));
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody Tecnico obj) {
		obj = this.tecnicoService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(this.tecnicoMapper.toDTO(obj)); //insere url de acesso ao objeto criado no Headers
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@Valid @RequestBody Tecnico obj, @PathVariable Long id){
		Tecnico newObj = new Tecnico();
		newObj = this.tecnicoService.update(obj,id);
		return ResponseEntity.ok().body(this.tecnicoMapper.toDTO(newObj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.tecnicoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
