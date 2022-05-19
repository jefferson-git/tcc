package br.com.mamute.cotacaoapi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity @Table(name = "colaborador")
@Getter @Setter @DiscriminatorValue("colaborador")
public class Colaborador extends PessoaFisica{		

	@NotBlank @Column(name = "formacao",length = 40)
	private String formacao;	
	
	@NotBlank @Column(name = "cargo", length = 100)
	private String cargo;		
	
	@Column(name = "empresa_anterior",nullable = true, length = 40)
	private String empresaAnterior;		

	@Column(nullable = true, length = 100)
	private String experiencia;	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_admissao", nullable = false)
	private LocalDate dataAdmissao;		
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_saida", nullable = true)
	private LocalDate dataSaida;	
			
	@Column(name = "modalidade_de_trabalho", length = 20, nullable = false)
	private String modalidadeDeTrabalho;	
	
	@Column(name = "salario")
	private Double salario;			
}