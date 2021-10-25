package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.mamute.cotacaoapi.model.Desconto;

public interface DescontoRepository extends JpaRepository<Desconto, Long>{

}
