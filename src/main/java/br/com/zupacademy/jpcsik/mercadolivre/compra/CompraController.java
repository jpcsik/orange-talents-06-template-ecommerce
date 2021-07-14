package br.com.zupacademy.jpcsik.mercadolivre.compra;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.jpcsik.mercadolivre.emails.Emails;
import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;
import br.com.zupacademy.jpcsik.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;

@RestController
public class CompraController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private Emails emails;
	
	@PostMapping("/comprar")
	@Transactional
	public ResponseEntity<?> comprar(@RequestBody @Valid NovaCompraRequest novaCompra, UriComponentsBuilder uriBuilder){
		
		Produto produto = produtoRepository.findById(novaCompra.getIdProduto()).get();
		Boolean abateu = produto.abaterEstoque(novaCompra.getQuantidade());

		if(abateu) {
			Usuario comprador = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Compra compra = novaCompra.toCompra(produto, comprador);
			compraRepository.save(compra);
			emails.mandarEmailNovaCompra(compra);
			return compra.novaUrlPagamento(uriBuilder);
		}
		return ResponseEntity.badRequest().body("Estoque insuficiente!");
	}

}
