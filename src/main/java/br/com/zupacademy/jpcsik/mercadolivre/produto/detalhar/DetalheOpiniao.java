package br.com.zupacademy.jpcsik.mercadolivre.produto.detalhar;

import br.com.zupacademy.jpcsik.mercadolivre.produto.opiniao.Opiniao;

public class DetalheOpiniao {

	private String titulo;
	private String descricao;

	public DetalheOpiniao(Opiniao opiniao) {
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
