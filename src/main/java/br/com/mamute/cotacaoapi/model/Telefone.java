package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "telefone")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Telefone implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@SequenceGenerator(name = "seq_telefone", sequenceName = "seq_id_telefone", allocationSize = 1)
	@GeneratedValue(generator = "seq_telefone")
	private Long id;
	
	@NotBlank(message = "O número deve ser informado." )
	@Size(min = 9, max = 16, message = "Informe o telefone corretamente.")
	@Column(length = 15, nullable = false)
	private String numero;
	
	@OneToOne 
	@JoinColumn(nullable = false, name = "descricao", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_descricao_telefone"))           
	private DescricaoTelefone descricao;

}
