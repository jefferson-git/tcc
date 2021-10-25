package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "departamento")
public class Departamento implements Serializable {

	@Id
	@SequenceGenerator(name = "seq_departamento", sequenceName = "seq_id_departamentoo", allocationSize = 1)
	@GeneratedValue(generator = "seq_departamento", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String nome;
	
	@Column(nullable = false)
	private String imagem;

}
