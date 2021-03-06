package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity @Table(name = "categoria")
@SuppressWarnings("serial") @Data
public class Categoria implements Serializable{

	@Id
	@SequenceGenerator(name = "seq_categoria", sequenceName = "seq_id_categoria", allocationSize = 1)
	@GeneratedValue(generator = "seq_categoria", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message = "O nome da categoria não pode ser nulo ou em branco.")
	@Size(min = 2, max = 30, message = "O nome da categoria de ter entre 2 e 30 caracteres.")
	private String nome;
	
	@NotBlank(message = "Insira alguma descrição para a nova categoria.")
	@Size(min = 4, max = 255, message = "O nome da categoria deve ter entre 4 e 255 caracteres.")
	private String descricao;
	  
	@OneToOne() @NotNull
	private Departamento departamento; 
}
