package br.com.zupacademy.jpcsik.mercadolivre.compra.fechamento;

public enum StatusPagseguro {

	SUCESSO, ERRO;

	public StatusTransacao normaliza() {
		if(this.equals(SUCESSO)) {
			return StatusTransacao.SUCESSO;
		}
		return StatusTransacao.ERRO;
	}
	
}
