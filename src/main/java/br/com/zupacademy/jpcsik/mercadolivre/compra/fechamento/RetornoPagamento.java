package br.com.zupacademy.jpcsik.mercadolivre.compra.fechamento;

import br.com.zupacademy.jpcsik.mercadolivre.compra.Compra;

public interface RetornoPagamento {

	Transacao toTransacao(Compra compra);

}
