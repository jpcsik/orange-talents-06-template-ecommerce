package br.com.zupacademy.jpcsik.mercadolivre.produto.opiniao;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;
import br.com.zupacademy.jpcsik.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;

@RestController
public class OpiniaoController {

	@Autowired
	private OpiniaoRepository opiniaoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping("/opiniao/cadastrar/{id}")
	@Transactional
	public ResponseEntity<?> cadastrar(@PathVariable Long id, @RequestBody @Valid NovaOpiniaoRequest novaOpiniao){
		
		Produto produto = produtoRepository.findById(id).get();
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Opiniao opiniao = novaOpiniao.toOpiniao(produto, usuario);
		opiniaoRepository.save(opiniao);
		
		return ResponseEntity.ok().build();
	}
	
}
