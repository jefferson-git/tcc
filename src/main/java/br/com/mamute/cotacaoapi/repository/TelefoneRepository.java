package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

}
