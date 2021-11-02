package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "lista_desejos")
@SuppressWarnings("serial")
public class ListaDesejos implements Serializable{

	@Id
	@SequenceGenerator(name = "seq_lista_desejos", sequenceName = "seq_id_lista_desejos", allocationSize = 1)
	@GeneratedValue(generator = "seq_lista_desejos", strategy = GenerationType.SEQUENCE)
	private long id;
	
	@ManyToOne
	private Produto produto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new Date();

}
