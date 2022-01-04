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

@Entity
@Data
@Table(name = "margem_lucro")
@SuppressWarnings("serial")
public class Lucro implements Serializable{
	
	@Id
	@EqualsAndHashCode.Include
	@SequenceGenerator(name = "seq_lucro", sequenceName = "seq_id_lucro", allocationSize = 1)
	@GeneratedValue(generator = "seq_lucro", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private Double porcentagem;
	
	@NotBlank(message = "O campo descrição não pode ser nulo ou vazio.")
	@Size(min = 4, max = 255, message = "O campo descrição deve ter entre 4 e 255 caracteres.")
	private String descricao;

}
