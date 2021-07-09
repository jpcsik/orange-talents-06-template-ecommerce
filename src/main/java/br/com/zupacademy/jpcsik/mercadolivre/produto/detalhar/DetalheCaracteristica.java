package br.com.zupacademy.jpcsik.mercadolivre.produto.detalhar;

import br.com.zupacademy.jpcsik.mercadolivre.produto.caracteristica.Caracteristica;

public class DetalheCaracteristica {

	private String nome;
	private String descricao;
	
	public DetalheCaracteristica(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
