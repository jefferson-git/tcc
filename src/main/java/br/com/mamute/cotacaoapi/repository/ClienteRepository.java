package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
