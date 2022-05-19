package br.com.mamute.cotacaoapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.TaxaEntrega;

public interface TaxaEntregaRepository extends JpaRepository<TaxaEntrega, Long>{

}
