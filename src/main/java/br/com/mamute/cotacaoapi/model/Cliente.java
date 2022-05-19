package br.com.mamute.cotacaoapi.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity @Table(name = "cliente")
@Getter @Setter @DiscriminatorValue("cliente")
public class Cliente extends PessoaFisica{
	@ManyToOne private CartaoDeCredito cartao;
}