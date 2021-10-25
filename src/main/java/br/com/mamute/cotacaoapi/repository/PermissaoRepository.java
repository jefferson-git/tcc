package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Integer>{

}
