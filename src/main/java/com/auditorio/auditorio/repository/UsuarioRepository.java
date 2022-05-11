package com.auditorio.auditorio.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.auditorio.auditorio.model.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>{
	
	public Usuario findByEmailAndSenha(String email, String senha);

}
