package br.com.mamute.cotacaoapi.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.mamute.cotacaoapi.repository.PermissaoRepository;

@SuppressWarnings("serial")
public class UsuarioAutenticacao implements UserDetails {

	@Autowired PermissaoRepository permissaoRepository;
	private Usuario usuario;	
	
	public UsuarioAutenticacao(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		Set<Papel> papeis = usuario.getPapeis();
		List<SimpleGrantedAuthority> autorizacoes = new ArrayList<>();
		
		for (Papel papel : papeis) {
			autorizacoes.add(new SimpleGrantedAuthority(papel.getNome()));
		}
		return autorizacoes;
	}

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		if(usuario.getStatus() == 1)
			return true;
		return false;
	}

}
