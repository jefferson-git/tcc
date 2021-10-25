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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estado")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "est_id")
	@EqualsAndHashCode.Include
	@SequenceGenerator(name = "seq_estado", sequenceName = "seq_id_estado",allocationSize = 1)
	@GeneratedValue(generator = "seq_estado", strategy = GenerationType.SEQUENCE)
	private Integer id;	
	
	@NotBlank(message = "O estado não pode ficar em branco.")
	@Size(min = 2, max = 30,message = "Nome do estado deve posuir entre duas e quarenta letras.")
	@Column(length = 30, nullable = false)
	private String nome;	

	@NotBlank(message = "A sigla não pode ficar em branco.")
	@Size(min = 2, max = 2, message = "A sigla deve posuir duas letras.")
	@Column(length = 2, unique = true, nullable = false)
	private String sigla;	

}