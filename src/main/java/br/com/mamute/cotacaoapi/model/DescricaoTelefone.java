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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "descricao_telefone")
@NoArgsConstructor
@Getter
@Setter
public class DescricaoTelefone implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seq_descricao_telefone", sequenceName = "seq_id_descricao_telefone", allocationSize = 1)
	@GeneratedValue(generator = "seq_tipo_telefone", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	
	@NotBlank(message = "A descrição deve ser informado.")
	@Size(min = 4, max = 40, message = "informe uma descrição entre 4 e 40 caracteres.")
	@Column(nullable = false, length = 40)
	private String nome;
}
