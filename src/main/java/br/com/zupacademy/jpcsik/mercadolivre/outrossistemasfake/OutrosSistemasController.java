package br.com.zupacademy.jpcsik.mercadolivre.outrossistemasfake;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutrosSistemasController {

	@PostMapping("/notas-fiscais")
	public void criaNota(@RequestBody @Valid NovaCompraNFRequest request) {
		System.out.println("Criando nota para compra: "+request.getIdCompra()+", Comprador: "+request.getIdComprador());
	}

	@PostMapping("/ranking")
	public void ranking(@RequestBody @Valid NovaCompraRankingRequest request) {
		System.out.println("Criando nota para compra: "+request.getIdCompra()+", Vendedor: "+request.getIdVendedor());
	}
	
}
