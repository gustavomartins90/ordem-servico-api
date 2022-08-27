package com.gustavo.os.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.os.domain.model.Pessoa;
import com.gustavo.os.domain.model.Tecnico;
import com.gustavo.os.domain.repository.PessoaRepository;
import com.gustavo.os.domain.repository.TecnicoRepository;
import com.gustavo.os.exception.DataIntegratyViolationException;
import com.gustavo.os.exception.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Long id) {
		return this.tecnicoRepository.findById(id).orElseThrow(()-> 
			new ObjectNotFoundException("Objeto não encontrado! ID: "+id+" Classe: "+Tecnico.class.getSimpleName()));
	}
	
	public List<Tecnico> findAll(){
		return this.tecnicoRepository.findAll();
	}

	public Tecnico create(Tecnico obj) {
		if(this.findByCPF(obj)!=null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		obj.setId(null);
		return this.tecnicoRepository.save(obj);
	}
	
	public Tecnico update(Tecnico obj, Long id) {
		Tecnico oldObj = this.findById(id);
		
		if(this.findByCPF(obj) != null && this.findByCPF(obj).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(obj.getNome());
		oldObj.setCpf(obj.getCpf());
		oldObj.setTelefone(obj.getTelefone());
		return this.tecnicoRepository.save(oldObj);
		
	}

	public void delete(Long id) {
		Tecnico obj = this.findById(id);
		if(obj.getListOrdemServico()!=null && obj.getListOrdemServico().size()>0) {
			throw new DataIntegratyViolationException("Tecnico não pode ser deletado, possui ordens de serviço");
		}
		this.tecnicoRepository.delete(obj);
	}
	
	private Pessoa findByCPF(Tecnico tecnico) {
		Pessoa obj = this.pessoaRepository.findByCPF(tecnico.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}

}
