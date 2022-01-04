package br.com.mamute.cotacaoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.mamute.cotacaoapi.model.Colaborador;
import br.com.mamute.cotacaoapi.model.Pessoa;
import br.com.mamute.cotacaoapi.model.Usuario;
import br.com.mamute.cotacaoapi.repository.ColaboradorRepository;
import br.com.mamute.cotacaoapi.repository.PessoaRepository;
import br.com.mamute.cotacaoapi.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired private UsuarioRepository usuarioRepository;
	@Autowired private PessoaRepository pessoaRepository;
	@Autowired private ColaboradorRepository colaboradorRepository;
	
	public Colaborador usuarioLogado() {		
    	Usuario usuario = usuarioRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    	Pessoa pessoa = pessoaRepository.findById(usuario.getId()).get();
    	return colaboradorRepository.findById(pessoa.getId()).get();
	}

}
