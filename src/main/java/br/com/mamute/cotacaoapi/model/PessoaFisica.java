package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "pessoa_fisica")
@Getter
@Setter
@DiscriminatorValue("pessoa_fisica")
@SuppressWarnings("serial")
public class PessoaFisica extends Pessoa implements Serializable {	
	

	@NotBlank(message = "O nome não pode ficar em branco")
	@Size(min = 1, max = 40, message = "O nome deve conter no mínimo uma letra no máximo quarenta.")
	@Column(length = 40, nullable = false)
	private String nome;	

	@NotBlank(message = "O sobrenome não pode ficar em branco")
	@Size(min = 1, max = 40, message = "O sobrenome deve conter no mínimo uma letra no máximo quarenta.")
	@Column(length = 40, nullable = false)
	private String sobrenome;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "O cpf não pode ficar em branco")
	private String rg;
	
	@NotBlank(message = "O cpf não pode ficar em branco")
	@Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
	@CPF()
	private String cpf;
	
	@NotBlank(message = "O sexo não pode ficar em branco")
	@Column(length = 20, nullable = false)
	private String sexo;		
}
