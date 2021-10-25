package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SuppressWarnings("serial")
@Table(name = "compra")
public class Compra implements Serializable{
	
	@Id
	@SequenceGenerator(name = "seq_compra", sequenceName = "seq_id_compra", allocationSize = 1)
	@GeneratedValue(generator = "seq_compra", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCompra = new Date();
	
	private Double ValorTotal = 0.;
	
	@ManyToOne
	private PessoaFisica pessoaFisica;
	
	@OneToOne
	private FormaPagamento formaPagamento;
}
