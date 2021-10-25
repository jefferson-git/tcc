package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "imposto")
@SuppressWarnings("serial")
public class Imposto implements Serializable{
	
	@Id
	@EqualsAndHashCode.Include
	@SequenceGenerator(name = "seq_imposto", sequenceName = "seq_id_imposto", allocationSize = 1)
	@GeneratedValue(generator = "seq_imposto", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private Float porcentagem;
		
	}

