package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.CartaoDeCredito;

public interface CartaoDeCreditoRepository extends JpaRepository<CartaoDeCredito, Long>{

}
