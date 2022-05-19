package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.FormaPagamento;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{

}
