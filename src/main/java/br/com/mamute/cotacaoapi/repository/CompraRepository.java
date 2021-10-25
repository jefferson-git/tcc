package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{

}
