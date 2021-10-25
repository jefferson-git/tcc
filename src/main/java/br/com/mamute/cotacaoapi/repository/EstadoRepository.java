package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.mamute.cotacaoapi.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer>{
}
