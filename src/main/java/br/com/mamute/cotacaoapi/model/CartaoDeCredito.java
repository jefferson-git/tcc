package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "cartao_de_credito")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuppressWarnings("serial")
public class CartaoDeCredito implements Serializable{
		
	@Id
	@SequenceGenerator(name = "seq_cartao", sequenceName = "seq_id_cartao", allocationSize = 1)
	@GeneratedValue(generator = "seq_cartao", strategy = GenerationType.SEQUENCE)
	private Long id;
		
	@Column(nullable = false, length = 20)
	private String numero;
	
	@DateTimeFormat(pattern = "MM/yyyy")
	private LocalDate dataVencimento;
	
	private String cvc;
}
