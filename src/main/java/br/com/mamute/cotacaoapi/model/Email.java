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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity @Data @Table(name = "email")
public class Email implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id	@Column(name = "id") @EqualsAndHashCode.Include
	@SequenceGenerator(name = "seq_email", sequenceName = "seq_id_email", allocationSize = 1)
	@GeneratedValue(generator = "seq_email", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "email")
	@javax.validation.constraints.Email(message = "Insira um email valido!")
	@Size(min = 5, max = 255, message = "O email deve possuir entre 5 e 255 letras!")
	@NotBlank(message = "O email não pode ficar em branco.")
	private String enderecoEmail;	
}