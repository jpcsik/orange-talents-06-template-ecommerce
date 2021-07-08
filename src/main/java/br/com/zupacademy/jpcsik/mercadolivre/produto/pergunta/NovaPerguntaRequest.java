package br.com.zupacademy.jpcsik.mercadolivre.produto.pergunta;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;

public class NovaPerguntaRequest {

	@NotBlank
	private String titulo;

	@JsonCreator(mode = Mode.PROPERTIES)
	public NovaPerguntaRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}
	
	public Pergunta toPergunta(Usuario usuario, Produto produto) {
		Pergunta pergunta = new Pergunta(
				this.titulo,
				usuario,
				produto);
		return pergunta;
	}
	
}
