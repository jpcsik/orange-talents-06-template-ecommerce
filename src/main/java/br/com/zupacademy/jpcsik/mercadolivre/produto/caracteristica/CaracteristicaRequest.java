package br.com.zupacademy.jpcsik.mercadolivre.produto.caracteristica;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;

public class CaracteristicaRequest {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

	@JsonCreator
	public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Caracteristica toCaracteristica(Produto produto){
		Caracteristica caracteristica = new Caracteristica(
					this.nome,
					this.descricao,
					produto
				);
		return caracteristica;
	}

}
