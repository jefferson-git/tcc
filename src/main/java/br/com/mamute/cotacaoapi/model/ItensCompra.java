package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@SuppressWarnings("serial")
@Table(name = "itens_compra")
public class ItensCompra implements Serializable{
	
	@Id
	@SequenceGenerator(name = "seq_itens_compra", sequenceName = "seq_id_itens_compra", allocationSize = 1)
	@GeneratedValue(generator = "seq_itens_compra", strategy = GenerationType.SEQUENCE)
	private Long id;
		
	private Integer quantidade = 0;
	
	private Double valorUnitario = 0.;
	
	private Double valorTotalItens = 0.;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Compra compra;
}
