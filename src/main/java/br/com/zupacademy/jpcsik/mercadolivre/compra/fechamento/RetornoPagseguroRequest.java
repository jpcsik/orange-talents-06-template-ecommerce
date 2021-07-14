package br.com.zupacademy.jpcsik.mercadolivre.compra.fechamento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.jpcsik.mercadolivre.compra.Compra;
import br.com.zupacademy.jpcsik.mercadolivre.validacao.ValorUnico;

public class RetornoPagseguroRequest implements RetornoPagamento{

	@NotBlank
	@ValorUnico(campo = "idTransacao", classeDeDominio = Transacao.class)
	private String idTransacao;
	@NotNull
	private StatusPagseguro status;

	@JsonCreator
	public RetornoPagseguroRequest(@NotNull String idTransacao, @NotNull StatusPagseguro status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}
	
	public Transacao toTransacao(Compra compra) {
		return new Transacao(this.status.normaliza(), this.idTransacao, compra);
	}
	
}
