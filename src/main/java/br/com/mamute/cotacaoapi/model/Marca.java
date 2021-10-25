package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SuppressWarnings("serial")
@Table(name = "marca")
public class Marca implements Serializable{
	
	@Id
	@SequenceGenerator(name = "seq_marca", sequenceName = "seq_id_marca", allocationSize = 1)
	@GeneratedValue(generator = "seq_marca", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message = "A marca deve ser informada!")
	@Size(min = 2, max = 100, message = "A marca deve possuir entre 2 e 100 caracteres!")
	private String nome;
	
	private String imagem;
}
