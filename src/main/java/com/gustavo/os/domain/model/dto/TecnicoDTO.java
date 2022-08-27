package com.gustavo.os.domain.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "Campo NOME é requerido")
	@Length(min = 3, max = 100, message = "O Campo NOME deve ter entre 3 e 100 caracteres")
	private String nome;
	
	@NotEmpty(message = "Campo CPF é requerido")
	@Length(min = 3, max = 14, message = "O Campo CPF deve ter 14 caracteres")
	@CPF
	private String cpf;
	
	@NotEmpty(message = "Campo TELEFONE é requerido")
	private String telefone;

	public TecnicoDTO() {
		super();
	}
	
}
