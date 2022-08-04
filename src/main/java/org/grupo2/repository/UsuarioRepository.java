package org.grupo2.repository;


import org.grupo2.model.Usuario;
import org.grupo2.model.enums.TipoSangue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Page<Usuario> findByTipoSanguineo(TipoSangue tipoSangue,Pageable pageable);

}
