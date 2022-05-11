package com.auditorio.auditorio.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.auditorio.auditorio.model.Usuario;
import com.auditorio.auditorio.repository.UsuarioRepository;
import com.auditorio.auditorio.util.HashUtil;




@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	
	@RequestMapping("cadastroUsuario")
	public String cadUsuario() {
		return "usuario/cadUsuario";
	}
	
	@RequestMapping(value = "salvarUsuario", method = RequestMethod.POST)
	public String salvarUsuario(Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			attr.addFlashAttribute("mensagemErro", "Verifique os campos!");
			return "redirect:cadastroUsuario";
		}
		
		// VERIFICA SE ESTÁ SENDO FEITA UMA ALTERAÇÃO AO INVÉS DE UMA INSERÇÃO
				boolean alteracao = usuario.getIdUsuario() != null ? true : false;
				
				// VERIFICA SE A SENHA ESTÁ VAZIA
				if(usuario.getSenha().equals(HashUtil.hash256(""))) {
					// SE NÃO FOR ALTERAÇÃO, EU DEFINO A PRIMEIRA PARTE DO E-MAIL COMO A SENHA
					if(!alteracao) {
						// EXTRAI A PARTE DO E-MAIL ANTES DO @
						String parte = usuario.getEmail().substring(0, usuario.getEmail().indexOf("@"));
						// DEFINE A SENHA DO ADMIN
						usuario.setSenha(parte);
					}else {
						// BUSCA A SENHA ATUAL
						String hash = repository.findById(usuario.getIdUsuario()).get().getSenha();
						usuario.setSenhaComHash(hash);
					}
				}
		
		try {
			repository.save(usuario);
			attr.addFlashAttribute("mensagemSucesso", "Usuário cadastrado com sucesso! ID - "+usuario.getIdUsuario() 
				+"  Caso a senha não tenha sido informada, a senha será a parte do e-mail antes do @!");
			return "redirect:cadastroUsuario";
		} catch (Exception e) {
			attr.addFlashAttribute("mensagemErro", "Houve um erro ao cadastrar o Usuário: "+e.getMessage());
		}
		
		return "redirect:cadastroUsuario";
		
		
	}
	
	@RequestMapping("alterarUsuario")
	public String alterar(Model model, Long id) {
		Usuario user = repository.findById(id).get();
		model.addAttribute("usuario", user);
		return "forward:cadastroUsuario";
		
	}
	
	@RequestMapping("excluirUsuario")
	public String excluirUsuaio(Long id) {
		repository.deleteById(id);
		return "redirect:listarUsuarios/1";
	}

	@RequestMapping("listarUsuarios/{page}")
	public String listaUsuarios(Model model, @PathVariable("page") int page) {
		
		   
		PageRequest pageable = PageRequest.of(page-1, 10, Sort.by(Sort.Direction.ASC, "nome"));
		
		Page<Usuario> pagina = repository.findAll(pageable);
		
		int totalPages = pagina.getTotalPages();
		
		List<Integer> pageNumbers = new ArrayList<Integer>();
		
		for(int i = 0; i < totalPages; i++) {
			pageNumbers.add(i+1);
		}
		
		model.addAttribute("usuarios", pagina.getContent());
		model.addAttribute("paginaAtual", page);
		model.addAttribute("totalPaginas", totalPages);
		model.addAttribute("numPaginas", pageNumbers);
		
		
		return "usuario/listaUsuarios";
	}

	
	
	@RequestMapping("loginUsuario")
	public String loginUsuario() {
		
		return "usuario/usuarioLogin";
	}
	
	@RequestMapping("login")
	public String login(Usuario usuarioLogin, RedirectAttributes attr, HttpSession session) {
		
		
				Usuario usuario = repository.findByEmailAndSenha(usuarioLogin.getEmail(), usuarioLogin.getSenha());
				
				if(usuario == null) {
					
					attr.addFlashAttribute("mensagemErro", "Login e/ou senha inválido");
					return "redirect:login";
				}else {
		
					session.setAttribute("usuarioLogado", usuario);
					// DEPOIS ALTERAR O RETURN PARA A PAGINA DE RESERVA
					return "redirect:cadastroUsuario";
				}		
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
}
