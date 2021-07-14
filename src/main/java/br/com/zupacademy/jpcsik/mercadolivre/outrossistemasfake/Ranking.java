package br.com.zupacademy.jpcsik.mercadolivre.outrossistemasfake;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.jpcsik.mercadolivre.compra.Compra;

@Service
public class Ranking {

	public static void processar(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		NovaCompraRankingRequest request = 
				new NovaCompraRankingRequest(compra.getId(), compra.getProduto().getProprietario().getId()); 
		
		restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
	}

}
