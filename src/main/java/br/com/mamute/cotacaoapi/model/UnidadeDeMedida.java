package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "unidade_de_medida")
public class UnidadeDeMedida implements Serializable{
	
	@Id
	@SequenceGenerator(name = "seq_unidade_de_media", sequenceName = "seq_id_unidade_de_medida", allocationSize = 1)
	@GeneratedValue(generator = "seq_unidade_de_media", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message = "Informe a unidade de medida!")
	@Size(min = 2, max = 15, message = "O campo deve ter entre 4 e 15 caracter!")
	@Column(unique = true, nullable = false)
	private String nome;
	
	@NotBlank(message = "Informe uma descrição!")
	@Size(min = 4, max = 255, message = "O campo deve ter entre 4 e 255 caracter!")
	private String descricao;

}
