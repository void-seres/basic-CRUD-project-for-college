package org.grupo2.service;

import java.util.List;

import org.grupo2.model.Usuario;
import org.grupo2.model.enums.TipoSangue;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioServiceInterface extends UserDetailsService{
	List<Usuario> getAllUsuarios();
	void cadastrarUsuario(Usuario usuario);
	Usuario getUsuarioById(long cpf);
	void deletarUsuarioById(long cpf);
	Page<Usuario> findPaginated(int pageNo, int pageSize);
	Page<Usuario> findPaginatedBySangue(int pageNo, int pageSize, TipoSangue tipoSangue);
}