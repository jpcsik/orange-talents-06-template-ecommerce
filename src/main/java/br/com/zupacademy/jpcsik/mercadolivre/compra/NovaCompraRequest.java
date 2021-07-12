package br.com.zupacademy.jpcsik.mercadolivre.compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;
import br.com.zupacademy.jpcsik.mercadolivre.validacao.Existe;

public class NovaCompraRequest {

	@NotNull
	@Positive
	private Integer quantidade;
	@NotNull
	@Existe(campo = "id", classeDeDominio = Produto.class)
	private Long idProduto;
	@NotNull
	private GatewayPagamento gateway;
	
	public NovaCompraRequest(@NotNull @Positive Integer quantidade, @NotNull Long idProduto, @NotNull GatewayPagamento gateway) {
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gateway;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	
	public Compra toCompra(Produto produto, Usuario comprador) {
		return new Compra(
				produto,
				this.quantidade,
				comprador,
				this.gateway,
				StatusCompra.INICIADA);
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}
	
}
