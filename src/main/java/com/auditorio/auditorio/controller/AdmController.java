package com.auditorio.auditorio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.auditorio.auditorio.model.Usuario;
import com.auditorio.auditorio.repository.UsuarioRepository;



@Controller
public class AdmController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@RequestMapping("pagLogin")
	public String paglogin() {
		return "adm/paglogin";
	}
	
	@RequestMapping("loginAdm")
	public String loginAdm(String senha, String nif) {
		
		String pass = "123";
		String n = "admin";
		
		if(senha.equals(n) && nif.equals(pass)) {
			return "redirect:cadastroUsuario";
		}
		
		System.out.println("senha incorreta");
		return "redirect:pagLogin";
	}
	
	

}
