package br.com.zupacademy.jpcsik.mercadolivre.compra;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

	PAGSEGURO{
		@Override
		public String criaUrlPagamento(UriComponentsBuilder uriBuilder, Compra compra) {
			UriComponents pagseguroUrl = uriBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(compra.getId().toString());
			return "pagseguro.com/"+compra.getId()+"?redirectUrl="+pagseguroUrl;
		}
	}, 
	
	PAYPAL{
		@Override
		public String criaUrlPagamento(UriComponentsBuilder uriBuilder, Compra compra) {
			UriComponents paypalUrl = uriBuilder.path("/retorno-paypal/{id}").buildAndExpand(compra.getId().toString());
			return "paypal.com/"+compra.getId()+"?redirectUrl="+paypalUrl;
		}
	};
	
	public String criaUrlPagamento(UriComponentsBuilder uriBuilder, Compra compra) {
		return this.criaUrlPagamento(uriBuilder, compra);
	}
	
}
