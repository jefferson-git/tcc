package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	boolean equals(Object setId);

}