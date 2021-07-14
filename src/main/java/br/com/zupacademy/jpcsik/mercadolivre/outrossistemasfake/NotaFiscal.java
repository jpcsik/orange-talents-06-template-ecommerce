package br.com.zupacademy.jpcsik.mercadolivre.outrossistemasfake;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.jpcsik.mercadolivre.compra.Compra;

@Service
public class NotaFiscal {

	public static void processar(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		NovaCompraNFRequest request = new NovaCompraNFRequest(compra.getId(), compra.getComprador().getId()); 
		
		restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
	}
	
}
