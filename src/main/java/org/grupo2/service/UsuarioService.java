package org.grupo2.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.grupo2.model.Usuario;
import org.grupo2.model.enums.TipoSangue;
import org.grupo2.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService implements UsuarioServiceInterface{

	@Autowired
	private UsuarioRepository repositorioDeUsuarios;
	
	@Override
	public List<Usuario> getAllUsuarios() {
		return repositorioDeUsuarios.findAll();
	}

	@Override
	public void cadastrarUsuario(Usuario usuario) {
		this.repositorioDeUsuarios.save(usuario);		
	}
	
	@Override
	public Usuario getUsuarioById(long cpf) {
		Optional<Usuario> optional = repositorioDeUsuarios.findById(cpf);
		
		Usuario usuario = null;
		
		if(optional.isPresent()) {
			usuario = optional.get();
		}else {
			throw new RuntimeException("Usuário de CPF " + cpf + " não foi encontrado.");
		}
		return usuario;
	}

	@Override
	public void deletarUsuarioById(long cpf) {
		this.repositorioDeUsuarios.deleteById(cpf);
	}
	
	@Override
	public Page<Usuario> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.repositorioDeUsuarios.findAll(pageable);
	}
	
	@Override
	public Page<Usuario> findPaginatedBySangue(int pageNo, int pageSize, TipoSangue tipoSangue) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.repositorioDeUsuarios.findByTipoSanguineo(tipoSangue, pageable);
	}
	
	
	//A parte de Cargos pode estar errada.
	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {//Considere Username como o CPF
		
		Usuario usuario = this.repositorioDeUsuarios.findById(Long.parseLong(cpf)).get();
		Collection<? extends GrantedAuthority> cargos = new ArrayList<GrantedAuthority>();
		
		if (usuario == null) {
			throw new UsernameNotFoundException("CPF invalido");
		}
		if (usuario.getFuncionario()) {
			cargos.stream().map(role -> new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
		}else {
			cargos.stream().map(role -> new SimpleGrantedAuthority("ROLE_DOADOR"));
		}
		return new User(cpf, org.grupo2.config.WebSecurityConfig.passwordEncoder().encode(usuario.getSenha()), cargos);
		
	}
}
	