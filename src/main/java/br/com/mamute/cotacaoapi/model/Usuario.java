package br.com.mamute.cotacaoapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity @Data @AllArgsConstructor @NoArgsConstructor
@SuppressWarnings("serial") @Table(name = "usuario")
public class Usuario implements Serializable{	

	@Id	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_id_usuario", allocationSize = 1)
	@GeneratedValue(generator = "seq_usuario", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "data_de_cadastro", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataCadastro = new Date();		
	private String password;		
	private String username;	
	@Column(nullable = false, length = 20)
	private Integer status;	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "permissao", 
			   joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), 
	           inverseJoinColumns = @JoinColumn(name = "papel_id", referencedColumnName = "id")) 
	private Set<Papel> papeis = new HashSet<>();
	
	public void setPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.password = passwordEncoder.encode(password);
	}
}
