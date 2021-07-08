package br.com.zupacademy.jpcsik.mercadolivre.produto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.jpcsik.mercadolivre.categoria.Categoria;
import br.com.zupacademy.jpcsik.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;

@RestController
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping("/produto/cadastrar")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoProdutoRequest novoProduto){
		
		Usuario proprietario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Categoria categoria = categoriaRepository.findById(novoProduto.getIdCategoria()).get();
		Produto produto = novoProduto.toProduto(categoria, proprietario);
		
		produtoRepository.save(produto);
		
		return ResponseEntity.ok().build();
		
	}
	
}
