package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity @SuppressWarnings("serial")
@Data @Table(name = "pix")
public class Pix implements Serializable{
	
	@Id	@SequenceGenerator(name = "seq_pix", sequenceName = "seq_id_pix_pagamento", allocationSize = 1)
	@GeneratedValue(generator = "seq_pix_", strategy = GenerationType.SEQUENCE)
	private Long id;	

	private String chaveAleatoria;
	
}
