package br.com.zupacademy.jpcsik.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.jpcsik.mercadolivre.categoria.Categoria;
import br.com.zupacademy.jpcsik.mercadolivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;
import br.com.zupacademy.jpcsik.mercadolivre.validacao.Existe;

public class NovoProdutoRequest {

	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@Min(1)
	private Integer quantidade;
	@Size(min=3)
	@NotNull
	private List<CaracteristicaRequest> caracteristicas;
	@NotBlank
	@Size(max=1000)
	private String descricao;
	@NotNull
	@Existe(campo = "id", classeDeDominio = Categoria.class)
	private Long idCategoria;

	@JsonCreator
	public NovoProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @Min(1) Integer quantidade, @Size(min = 3) @NotNull List<CaracteristicaRequest> caracteristicas,
			@NotBlank @Size(max = 1000) String descricao, @NotNull Long idCategoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public Produto toProduto(Categoria categoria, Usuario proprietario) {
		
		Produto produto = new Produto(
				this.nome,
				this.valor,
				this.quantidade,
				this.caracteristicas,
				this.descricao,
				categoria,
				proprietario);
		return produto;
	}
	
}
