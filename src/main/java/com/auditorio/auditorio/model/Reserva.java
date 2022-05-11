package com.auditorio.auditorio.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReserva;
	private String tituloEvento;
	private String descricao;
	private Calendar dataEvento;
	private boolean manha;
	private boolean tarde;
	private boolean noite;
	@ManyToOne
	private Usuario usuario;
	
	
}
