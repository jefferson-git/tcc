package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
}
