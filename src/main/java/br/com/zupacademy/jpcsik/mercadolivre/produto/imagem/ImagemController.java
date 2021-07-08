package br.com.zupacademy.jpcsik.mercadolivre.produto.imagem;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;
import br.com.zupacademy.jpcsik.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;

@RestController
public class ImagemController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private Uploader bancoImagensFake;
	
	@PostMapping("imagem/cadastrar/{id}")
	@Transactional
	public ResponseEntity<?> cadastrar(@PathVariable Long id, @Valid NovaImagemRequest novaImagem){

		if(!produtoRepository.existsById(id)) {
			return ResponseEntity.badRequest().body("Produto não existe!");
		}
		
		Set<String> links = bancoImagensFake.salvar(novaImagem.getImagens()); 
		
		Usuario proprietario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Produto produto = produtoRepository.findById(id).get();

		if(!produto.pertenceAoProprietario(proprietario)) {
			return ResponseEntity.status(403).body("Usuario não autorizado!");
		}
		
		produto.salvarImagens(links);
		produtoRepository.save(produto);
		
		return ResponseEntity.ok().build();
	}
	
}
