package com.auditorio.auditorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.auditorio.auditorio.repository.ReservaRepository;

@Controller
public class ReservaController {
	
	@Autowired
	private ReservaRepository repository;
	
	@RequestMapping("cadastroReserva")
	public String cadReserva() {
		return "reserva/reserva";
	}
	

}
