package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SuppressWarnings("serial")
@Table(name = "carrinho")
public class Carrinho implements Serializable{
	
	@Id
	@SequenceGenerator(name = "seq_carrinho", sequenceName = "seq_id_carrinho", allocationSize = 1)
	@GeneratedValue(generator = "seq_carrinho", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private Produto produto;
}
