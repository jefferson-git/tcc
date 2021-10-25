package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SuppressWarnings("serial")
@Table(name = "comentario_do_produto")
public class ComentarioDoProduto implements Serializable{
	
	@Id
	@SequenceGenerator(name = "seq_comentario_do_produto", sequenceName = "seq_id_comentario_do_produto", allocationSize = 1)
	@GeneratedValue(generator = "seq_comentario_do_produto", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Size(max = 255, message = "O comentário deve possuir no máximo 255 caracteres!")
	private String comentario;

}
