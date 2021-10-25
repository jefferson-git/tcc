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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "desconto")
@SuppressWarnings("serial")
public class Desconto implements Serializable{
	
	@Id
	@EqualsAndHashCode.Include
	@SequenceGenerator(name = "seq_desconto", sequenceName = "seq_id_desconto", allocationSize = 1)
	@GeneratedValue(generator = "seq_desconto", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private Double porcentage;
	
	@NotBlank(message = "O campo descrição não pode ser nulo ou vazio.")
	@Size(min = 4, max = 255, message = "O campo descrição deve ter entre 4 e 255 caracteres.")
	private String descricao;

}
