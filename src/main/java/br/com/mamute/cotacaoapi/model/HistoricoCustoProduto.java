package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SuppressWarnings("serial")
@Table(name = "historico_de_custo_do_produto")
public class HistoricoCustoProduto implements Serializable{
	
	@Id
	@SequenceGenerator(name = "seq_historico_de_custo_do_produto", sequenceName = "seq_id_historico_de_custo_do_produto", allocationSize = 1)
	@GeneratedValue(generator = "seq_historico_de_custo_do_produto", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "data_inicial")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate DataInicial;
	
	@Column(name = "data_final")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate DataFinal;
	
	@Column(nullable = false)
	private BigDecimal preco;
}
