package br.com.zupacademy.jpcsik.mercadolivre.produto.imagem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;

@Entity
public class ImagemProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String link;
	@NotNull
	@ManyToOne
	private Produto produto;

	@Deprecated
	public ImagemProduto() {
	}

	public ImagemProduto(@NotBlank String link, @NotNull Produto produto) {
		this.link = link;
		this.produto = produto;
	}
	
}
