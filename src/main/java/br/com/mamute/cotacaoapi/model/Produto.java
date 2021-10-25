package br.com.mamute.cotacaoapi.model;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuppressWarnings("serial")
@Table(name = "produto")
public class Produto implements Serializable{
	
	@Id
	@EqualsAndHashCode.Include
	@SequenceGenerator(name = "seq_produto", sequenceName = "seq_id_produto", allocationSize = 1)
	@GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message = "O campo nome deve ser informada!")
	@Column(nullable = false)
	private String nome;
	
	private Integer quantidade;
	
	private String imagem;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = true)
	private Date dataCadastro = new Date();
	
	@Column(name = "preco_custo", nullable = false)
	private Double precoCusto;
		
	@Transient
	private String precoFinal;
	
	@Column(name = "preco_venda",nullable = false)
	private Double precoVenda;
			
	@NotBlank(message = "O campo cor deve ser informada!")
	@Column(nullable = false, length = 40)
	private String cor;
	
	@Size(max = 255, message = "A descrição deve possuir no máximo 255 caracteres!")
	private String descricao;
	
	@OneToOne()
	private Desconto desconto;
	
	@OneToOne()
	private Lucro lucro;
	
	@OneToOne()
	private Categoria categoria;
	
	@OneToOne ()
	private Imposto imposto;
	
	@OneToOne()
	private UnidadeDeMedida unidadeDeMedida;
	
	@OneToOne
	private Marca marca;

	@OneToMany()
	private List<HistoricoCustoProduto> historicoCustoProduto;
	
	@OneToMany()
	private List<HistoricoTransacoesProduto> historicoTransacoesProduto;
	
}
