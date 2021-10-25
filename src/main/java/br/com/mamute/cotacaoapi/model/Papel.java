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
@Table(name = "papel")
@SuppressWarnings("serial")
public class Papel implements Serializable{
	
	@Id
	@SequenceGenerator(name = "seq_papel", sequenceName = "seq_id_papel",allocationSize = 1)
	@GeneratedValue(generator = "seq_papel", strategy = GenerationType.SEQUENCE)
	private Integer id;		
	
	@NotBlank(message = "O nome não pode ficar em branco.")
	@Size(min = 2, max = 50, message = "O nome do papel deve conter entre 2 e 50 caracteres.")
	@Column(length = 50, nullable = false, unique = true)
	private String nome;
	
}