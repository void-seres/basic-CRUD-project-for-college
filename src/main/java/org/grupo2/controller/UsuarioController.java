package org.grupo2.controller;

import java.util.List;
import org.grupo2.model.Usuario;
import org.grupo2.model.enums.Sexo;
import org.grupo2.model.enums.TipoSangue;
import org.grupo2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@Autowired
	private UsuarioService usuarioService;
	
	// Mostra uma lista de usuários
	@GetMapping("/")
	public String viewHomePage(Model model, @Param("tipo") String tipo) {
		//model.addAttribute("listaUsuarios", usuarioService.getAllUsuarios()); Substituido pela paginação
		//return "index"; 														Substituido pela paginação
		return findPaginated(1, model, tipo);
	}
	
	@GetMapping("/formularioDeNovoUsuario") //Todavez que entrar /formularioDeNovoUsuario na URL, essa função sera executada, e retornará a paginá novo_usuario.html
	public String formularioNovoUsuario(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		model.addAttribute("tiposSanguineo", TipoSangue.values());
		model.addAttribute("sexos", Sexo.values());
		return "novo_usuario";
	}
	
	@PostMapping("/cadastrarUsuario")
	public String cadastrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
		//Salvar usuário para a database.
		usuarioService.cadastrarUsuario(usuario);
		return "redirect:/";
	}
	
	@GetMapping("/atualizarUsuario/{cpf}")
	public String atualizarUsuario(@PathVariable (value = "cpf") long cpf, Model model) {
		//pega o usuario do serviço
		Usuario usuario = usuarioService.getUsuarioById(cpf);
		
		//define o usuario como um atributo-modelo para pré-popular o formulário
		model.addAttribute("usuario", usuario);
		model.addAttribute("tiposSanguineo", TipoSangue.values());
		model.addAttribute("sexos", Sexo.values());
		return "atualizar_usuario";
	}
	
	@GetMapping("/deletarUsuario/{cpf}")
	public String deletarUsuario(@PathVariable (value = "cpf") long cpf) {
		this.usuarioService.deletarUsuarioById(cpf);
		return "redirect:/";
	}
	
//	@GetMapping("/page/{pageNo}")
//	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model) {
//		int pageSize = 5;
//		
//		Page<Usuario> page = usuarioService.findPaginated(pageNo, pageSize);
//		List<Usuario> listaUsuarios = page.getContent();
//		
//		model.addAttribute("currentPage", pageNo);
//		model.addAttribute("totalPages", page.getTotalPages());
//		model.addAttribute("totalItems", page.getTotalElements());
//		model.addAttribute("tiposSanguineo", TipoSangue.values());
//		
//		
//		model.addAttribute("listaUsuarios", listaUsuarios);
//
//		return "index";
//	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model, String tipoSangueString) {
		int pageSize = 5;
		model.addAttribute("currentPage", pageNo);
		
		if(tipoSangueString!=null)//Se Haver um tipo de Sangue Selecionado
		{
			TipoSangue tipo= TipoSangue.valueOf(tipoSangueString);//Converte String Para Enum
			Page<Usuario> page = usuarioService.findPaginatedBySangue(pageNo, pageSize, tipo);
			List<Usuario> listaPSangue = page.getContent();
			model.addAttribute("listaUsuarios", listaPSangue);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			model.addAttribute("tiposSanguineo", TipoSangue.values());
		}
		else {
			Page<Usuario> page = usuarioService.findPaginated(pageNo, pageSize);
			List<Usuario> listaUsuarios = page.getContent();
			model.addAttribute("listaUsuarios", listaUsuarios);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			model.addAttribute("tiposSanguineo", TipoSangue.values());
			
		}
		
		
		
		

		return "index";
	}
	
//	@GetMapping("/pesquisarPorSangue/{tipoSangue}")
//	public String pesquisarPorSangue(@PathVariable (value = "tipoSangue") String tipoSangue)
//	{
//		return "pesquisa";
//	}
}
