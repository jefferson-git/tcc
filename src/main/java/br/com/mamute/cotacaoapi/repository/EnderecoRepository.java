package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
