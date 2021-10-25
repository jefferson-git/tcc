package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.mamute.cotacaoapi.model.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{

}
