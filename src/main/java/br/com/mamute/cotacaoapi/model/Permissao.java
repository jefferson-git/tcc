package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;


import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "permissao")
@SuppressWarnings("serial")
public class Permissao implements Serializable{
		
	@Id
	@Column(name = "per_id")
	@SequenceGenerator(name = "seq_permissao", sequenceName = "seq_id_permissao", allocationSize = 1)
	@GeneratedValue(generator = "seq_permissao", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDaPermissao = new Date();
	
	@Valid
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	private Usuario usuario;
	
	@Valid
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	private Papel papel;

}
