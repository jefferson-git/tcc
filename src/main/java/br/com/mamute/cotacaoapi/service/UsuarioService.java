package br.com.mamute.cotacaoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.model.Colaborador;
import br.com.mamute.cotacaoapi.model.Pessoa;
import br.com.mamute.cotacaoapi.model.Usuario;
import br.com.mamute.cotacaoapi.repository.ClienteRepository;
import br.com.mamute.cotacaoapi.repository.ColaboradorRepository;
import br.com.mamute.cotacaoapi.repository.PessoaRepository;
import br.com.mamute.cotacaoapi.repository.UsuarioRepository;

@Service
public class UsuarioService { 
	
	@Autowired private UsuarioRepository usuarioRepository;
	@Autowired private ClienteRepository clienteRepository;
	@Autowired private PessoaRepository pessoaRepository;
	@Autowired private ColaboradorRepository colaboradorRepository;
	
	public Colaborador usuarioLogado() {		
    	Usuario usuario = usuarioRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    	for (Pessoa pessoa : pessoaRepository.findAll()) {
			if(pessoa.getUsuario().getId() == usuario.getId())
		       	return colaboradorRepository.findById(pessoa.getId()).get();
		} 
    	throw new UsernameNotFoundException("usuário não encontrado");
	}

	public Cliente ClienteLogado() {		
    	Usuario usuario = usuarioRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());	
    	for (Pessoa pessoa : pessoaRepository.findAll()) {
			if(pessoa.getUsuario().getId() == usuario.getId())
		       	return clienteRepository.findById(pessoa.getId()).get();
		}    	
    	throw new UsernameNotFoundException("usuário não encontrado");
	}
}
