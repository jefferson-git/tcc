package br.com.mamute.cotacaoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mamute.cotacaoapi.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{
	
	@Query("SELECT c FROM Compra c where cliente_pes_id = :id ")
	List<Compra> listarCompras(@Param("id") Long id);
}
