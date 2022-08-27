package com.gustavo.os.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.os.domain.model.Cliente;
import com.gustavo.os.domain.model.Pessoa;
import com.gustavo.os.domain.repository.ClienteRepository;
import com.gustavo.os.domain.repository.PessoaRepository;
import com.gustavo.os.exception.DataIntegratyViolationException;
import com.gustavo.os.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Cliente findById(Long id) {
		return this.clienteRepository.findById(id).orElseThrow(()-> 
			new ObjectNotFoundException("Objeto não encontrado! ID: "+id+" Classe: "+Cliente.class.getSimpleName()));
	}
	
	public List<Cliente> findAll(){
		return this.clienteRepository.findAll();
	}

	public Cliente create(Cliente obj) {
		if(this.findByCPF(obj)!=null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		obj.setId(null);
		return this.clienteRepository.save(obj);
	}
	
	public Cliente update(Cliente obj, Long id) {
		Cliente oldObj = this.findById(id);
		
		if(this.findByCPF(obj) != null && this.findByCPF(obj).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(obj.getNome());
		oldObj.setCpf(obj.getCpf());
		oldObj.setTelefone(obj.getTelefone());
		return this.clienteRepository.save(oldObj);
		
	}

	public void delete(Long id) {
		Cliente obj = this.findById(id);
		if(obj.getListOrdemServico()!=null && obj.getListOrdemServico().size()>0) {
			throw new DataIntegratyViolationException("Cliente não pode ser deletado, possui ordens de serviço");
		}
		this.clienteRepository.delete(obj);
	}
	
	private Pessoa findByCPF(Cliente cliente) {
		Pessoa obj = this.pessoaRepository.findByCPF(cliente.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}

}
