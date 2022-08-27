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

import com.gustavo.os.domain.model.Cliente;
import com.gustavo.os.domain.model.dto.ClienteDTO;
import com.gustavo.os.mapper.ClienteMapper;
import com.gustavo.os.service.ClienteService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteMapper clienteMapper;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		Cliente obj = this.clienteService.findById(id);
		return ResponseEntity.ok().body(this.clienteMapper.toDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> listCliente = this.clienteService.findAll();
		return ResponseEntity.ok().body(this.clienteMapper.toListDTO(listCliente));
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody Cliente obj) {
		obj = this.clienteService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(this.clienteMapper.toDTO(obj)); //insere url de acesso ao objeto criado no Headers
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@Valid @RequestBody Cliente obj, @PathVariable Long id){
		Cliente newObj = new Cliente();
		newObj = this.clienteService.update(obj,id);
		return ResponseEntity.ok().body(this.clienteMapper.toDTO(newObj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
