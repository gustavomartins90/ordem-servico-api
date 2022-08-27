package com.gustavo.os.domain.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gustavo.os.domain.enuns.Prioridade;
import com.gustavo.os.domain.enuns.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemServicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFechamento;
	private Integer prioridade;
	
	@NotEmpty(message = "Observações é obrigatório")
	@Length(min = 3, max = 255, message = "O Campo Observações deve ter entre 3 e 255 caracteres")
	private String observacoes;
	private Integer status;	
	private TecnicoDTO tecnico;	
	private ClienteDTO cliente;

	public OrdemServicoDTO() {
		super();
	}
	
	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}
	
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade.getCod();
	}
	
	public Status getStatus() {
		return Status.toEnum(this.status);
	}
	
	public void setStatus(Status status) {
		this.status = status.getCod();
	}
	
}
