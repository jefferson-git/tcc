package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
