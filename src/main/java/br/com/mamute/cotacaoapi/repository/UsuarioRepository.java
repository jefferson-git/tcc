package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.mamute.cotacaoapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario  findByUsername (String  username);	
}
