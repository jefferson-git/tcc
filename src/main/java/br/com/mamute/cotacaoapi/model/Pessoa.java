package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
	

@Entity @Getter @Setter
@Table(name = "pessoa")
@SuppressWarnings("serial")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_entidade")
public abstract class Pessoa implements Serializable{	
	
	@Id
	@Column(name = "pes_id")
	@EqualsAndHashCode.Include
	@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_id_pessoa", allocationSize = 1)
	@GeneratedValue(generator = "seq_pessoa", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "imagem")
	private String imagem;	
	
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})	
	private Telefone telefone;
	
	@Valid @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private Endereco endereco;
	
	@Valid @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})	
	private Email email;
	
	@Valid @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})	
	private Usuario usuario;
}
