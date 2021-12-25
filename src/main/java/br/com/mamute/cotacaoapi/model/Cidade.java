package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cidade")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cid_id")
	@EqualsAndHashCode.Include
	@SequenceGenerator(name = "seq_cidade", sequenceName = "seq_id_cidade",allocationSize = 1)
	@GeneratedValue(generator = "seq_cidade", strategy = GenerationType.SEQUENCE)
	private Integer id;	
	
	@NotBlank(message = "A cidade não pode ficar em branco.")
	@Size(min = 2, max = 40, message = "A cidade deve ter entre duas e quarenta letras.")
	@Column(length = 40, nullable = false)
	private String nome;
	
	@ManyToOne()
	@JoinColumn(name = "estado_id", referencedColumnName = "est_id", foreignKey = @ForeignKey(name = "fk_estado"))           
	private Estado estado;
}