package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@SuppressWarnings("serial") @Data
@Entity(name = "taxa_entrega")
public class TaxaEntrega implements Serializable{

	@Id @SequenceGenerator(name = "seq_taxa_entrega", sequenceName = "seq_id_taxa_entrega",allocationSize = 1)
	@GeneratedValue(generator = "seq_taxa_entrega", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message = "O nome do método de envio não pode ficar em branco.")
	@Size(min = 2, max = 40, message = "O nome deve ter entre duas e quarenta letras.")
	@Column(length = 40, nullable = false)
	private String nome;	
	@NotNull private Double valor;	
	@NotNull private String tempoMinimo;
	@NotNull private String tempoMaximo; 
	
}
