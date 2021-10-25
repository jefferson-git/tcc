package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{

}
