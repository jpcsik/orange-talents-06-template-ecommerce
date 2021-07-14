package br.com.zupacademy.jpcsik.mercadolivre.compra.fechamento;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FechaCompraController {

	@Autowired
	private ProcessarCompra processarCompra;
	
	@PostMapping("/retorno-pagseguro/{id}")
	@Transactional
	public ResponseEntity<?> fechandoPagseguro(@PathVariable Long id, @RequestBody @Valid RetornoPagseguroRequest retorno){
		return processarCompra.processar(id, retorno);
	}
	
	@PostMapping("/retorno-paypal/{id}")
	@Transactional
	public ResponseEntity<?> fechandoPaypal(@PathVariable Long id, @RequestBody @Valid RetornoPaypalRequest retorno){
		return processarCompra.processar(id, retorno);
	}
	
}
