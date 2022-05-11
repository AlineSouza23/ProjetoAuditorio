package com.auditorio.auditorio.model;

public enum Identificacao {
	
	FUNCIONARIO ("Funcionário"),
	VISITANTE ("Visitante");

	String i;
	
	private Identificacao(String i) {
		this.i = i;
	}
			

}
