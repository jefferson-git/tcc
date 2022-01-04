package br.com.mamute.cotacaoapi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.mamute.cotacaoapi.repository.UsuarioRepository;

public class implementaUsuario implements UserDetailsService{

	@Autowired UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("usuário não encontrado");
		}
		return new UsuarioAutenticacao(usuario);
	}

}
