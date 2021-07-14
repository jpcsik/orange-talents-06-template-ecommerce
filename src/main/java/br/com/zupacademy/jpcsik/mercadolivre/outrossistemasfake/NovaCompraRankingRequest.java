package br.com.zupacademy.jpcsik.mercadolivre.outrossistemasfake;

import javax.validation.constraints.NotNull;

public class NovaCompraRankingRequest {

	@NotNull
	private Long idCompra;
	@NotNull
	private Long idVendedor;

	public NovaCompraRankingRequest(@NotNull Long idCompra, @NotNull Long idVendedor) {
		this.idCompra = idCompra;
		this.idVendedor = idVendedor;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public Long getIdVendedor() {
		return idVendedor;
	}

}
