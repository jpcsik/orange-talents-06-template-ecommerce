package br.com.zupacademy.jpcsik.mercadolivre.produto.opiniao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Min(1)
	@Max(5)
	private Integer nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Column(length = 500)
	private String descricao;
	@NotNull
	@ManyToOne
	private Produto produto;
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@Deprecated
	public Opiniao() {
	}

	public Opiniao(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull Produto produto, Usuario usuario) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
