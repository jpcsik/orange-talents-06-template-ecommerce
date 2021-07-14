 package br.com.zupacademy.jpcsik.mercadolivre.produto.detalhar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;
import br.com.zupacademy.jpcsik.mercadolivre.produto.ProdutoRepository;

@RestController
public class DetalharController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/produto/detalhar/{id}")
	public ResponseEntity<DetalheProdutoResponse> detalhar(@PathVariable Long id){
		
		Produto produto = produtoRepository.findById(id).get();
		return ResponseEntity.ok(new DetalheProdutoResponse(produto, produtoRepository));
	}
	
}
