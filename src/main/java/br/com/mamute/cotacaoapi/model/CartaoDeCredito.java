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
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity @Table(name = "cartao_de_credito")
@Data @SuppressWarnings("serial") 
@AllArgsConstructor @NoArgsConstructor
public class CartaoDeCredito implements Serializable{
		
	@Id	@SequenceGenerator(name = "seq_cartao", sequenceName = "seq_id_cartao", allocationSize = 1)
	@GeneratedValue(generator = "seq_cartao", strategy = GenerationType.SEQUENCE)
	private Long id;		
	private String numero;	
	private String nomeCompleto;	
	private String dataVencimento;	
	private String cvc;
}
