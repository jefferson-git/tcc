package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SuppressWarnings("serial")
@Table(name = "forma_pagamento")
public class FormaPagamento implements Serializable{
	
	@Id
	@SequenceGenerator(name = "seq_forma_pagamento", sequenceName = "seq_id_forma_pagamento", allocationSize = 1)
	@GeneratedValue(generator = "seq_forma_pagamento", strategy = GenerationType.SEQUENCE)
	private Integer id;	
	
	@OneToOne
	private CartaoDeCredito cartaoDeCredito;
}
