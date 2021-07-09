package br.com.zupacademy.jpcsik.mercadolivre.produto.caracteristica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;

@Entity
public class Caracteristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public Caracteristica() {
	}

	public Caracteristica(@NotBlank String nome, @NotBlank String descricao, Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
