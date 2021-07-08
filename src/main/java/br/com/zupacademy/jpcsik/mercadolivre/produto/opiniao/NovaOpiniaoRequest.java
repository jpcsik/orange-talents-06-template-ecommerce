package br.com.zupacademy.jpcsik.mercadolivre.produto.opiniao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;

public class NovaOpiniaoRequest {

	@NotNull
	@Min(1)
	@Max(5)
	private Integer nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Size(max=500)
	private String descricao;
	
	@JsonCreator
	public NovaOpiniaoRequest(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public Opiniao toOpiniao(Produto produto, Usuario usuario) {
		Opiniao opiniao = new Opiniao(
				this.nota,
				this.titulo,
				this.descricao,
				produto,
				usuario);
		return opiniao;
	}
	
}
