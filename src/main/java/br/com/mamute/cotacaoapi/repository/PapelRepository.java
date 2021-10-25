package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Integer>{

}
