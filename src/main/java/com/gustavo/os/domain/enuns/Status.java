package com.gustavo.os.domain.enuns;

import lombok.Getter;

@Getter
public enum Status {
	ABERTO(0, "ABERTO"),
	ANDAMENTO(1, "ANDAMENTO"),
	ENCERRADO(2, "ENCERRADO");
	
	private Integer cod;
	private String descricao;
	
	
	private Status(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static Status toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			
			if(cod.equals(x.getCod())){
				return x;	
			}
		}
		throw new IllegalArgumentException("Status Invalido! "+cod);
	}
	
}
