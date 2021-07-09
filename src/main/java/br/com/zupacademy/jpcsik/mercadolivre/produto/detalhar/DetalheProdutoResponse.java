package br.com.zupacademy.jpcsik.mercadolivre.produto.detalhar;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;
import br.com.zupacademy.jpcsik.mercadolivre.produto.ProdutoRepository;

public class DetalheProdutoResponse {

	private Set<String> links;
	private String nome;
	private BigDecimal preco;
	private List<DetalheCaracteristica> caracteristicas;
	private String descricao;
	private Double mediaNotas;
	private Integer totalDeNotas;
	private List<DetalheOpiniao> opinioes;
	private List<String> perguntas;

	public DetalheProdutoResponse(Produto produto, ProdutoRepository produtoRepository) {
		this.links = produto.mapImagens(imagem -> imagem.getLink());
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.caracteristicas = produto.mapCaracteristicas(DetalheCaracteristica::new);
		this.descricao = produto.getDescricao();
		this.mediaNotas = produtoRepository.mediaDeNotasPorProduto(produto.getId());
		this.totalDeNotas = produtoRepository.totalDeNotasPorProduto(produto.getId());
		this.opinioes = produto.mapOpiniao(DetalheOpiniao::new);
		this.perguntas = produto.mapPergunta(pergunta -> pergunta.getTitulo());
	}

	public Set<String> getLinks() {
		return links;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public List<DetalheCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getMediaNotas() {
		return mediaNotas;
	}

	public Integer getTotalDeNotas() {
		return totalDeNotas;
	}

	public List<DetalheOpiniao> getOpinioes() {
		return opinioes;
	}

	public List<String> getPerguntas() {
		return perguntas;
	}

}
