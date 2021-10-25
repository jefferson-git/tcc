package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{

}
