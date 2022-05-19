package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @SuppressWarnings("serial")
@Data @Table(name = "endereco")
@NoArgsConstructor @AllArgsConstructor
public class Endereco implements Serializable{
	
	@Id @Column(name = "id")
	@SequenceGenerator(name = "seq_end", sequenceName = "seq_id_endereco", allocationSize = 1)
	@GeneratedValue(generator = "seq_end", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	//@Column(length = 10, nullable = false)
	//@NotBlank(message = "O cep não pode ficar em branco.")
	private String cep;
	
	//@Column(length = 40, nullable = false)
	//@NotBlank(message = "O logradouro não pode ficar em branco.")
	private String logradouro;
	
	@Size(max = 255, message = "O complemento tem a limitação de 255 caracteres!")
	private String complemento;
	
	@Size(max = 10, message = "O número tem a limitação de dez caracteres!") 
	private String numero;
	
	//@Column(length = 40, nullable = false)
	//@NotBlank(message = "O bairro não pode ficar em branco ou nulo.")
	private String bairro;
	
	private int principal;
	
	//@Column(length = 40, nullable = false)
	//@NotBlank(message = "A cidade não pode ficar em branco ou nulo.")
	private String cidade;
	
	//@Column(length = 40, nullable = false)
	//@NotBlank(message = "O estado não pode ficar em branco ou nulo.")
	private String estado;
	
	/*
	@ManyToOne
	@JoinColumn(name = "cidade_id", referencedColumnName = "cid_id", foreignKey = @ForeignKey(name = "fk_cidade"))
	private Cidade cidade;
	*/
}
