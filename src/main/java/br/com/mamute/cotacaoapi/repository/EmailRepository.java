package br.com.mamute.cotacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mamute.cotacaoapi.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long>{

}
