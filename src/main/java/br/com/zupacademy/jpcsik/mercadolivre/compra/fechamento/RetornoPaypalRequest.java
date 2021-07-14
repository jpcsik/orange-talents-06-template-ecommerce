package br.com.zupacademy.jpcsik.mercadolivre.compra.fechamento;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.jpcsik.mercadolivre.compra.Compra;
import br.com.zupacademy.jpcsik.mercadolivre.validacao.ValorUnico;

public class RetornoPaypalRequest implements RetornoPagamento {

	@NotNull
	@Min(0)
	@Max(1)
	private Integer status;
	@NotBlank
	@ValorUnico(campo = "idTransacao", classeDeDominio = Transacao.class)
	private String idTransacao;
	
	public RetornoPaypalRequest(@NotNull @Min(0) @Max(1) Integer status, @NotBlank String idTransacao) {
		this.status = status;
		this.idTransacao = idTransacao;
	}
	
	public Transacao toTransacao(Compra compra) {
		StatusTransacao statusTransacao;
		
		if(status == 0) {
			statusTransacao = StatusTransacao.ERRO;
		}else {
			statusTransacao = StatusTransacao.SUCESSO;
		}
		
		return new Transacao(statusTransacao, this.idTransacao, compra);
	}
}
