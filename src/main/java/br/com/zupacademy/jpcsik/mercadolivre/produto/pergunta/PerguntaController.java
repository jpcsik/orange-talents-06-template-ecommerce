package br.com.zupacademy.jpcsik.mercadolivre.produto.pergunta;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.jpcsik.mercadolivre.emails.Emails;
import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;
import br.com.zupacademy.jpcsik.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;

@RestController
public class PerguntaController {

	@Autowired
	private PerguntaRepository perguntaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private Emails emails;
	
	
	@PostMapping("pergunta/cadastrar/{id}")
	@Transactional
	public ResponseEntity<?> cadastrar(@PathVariable Long id,@RequestBody @Valid NovaPerguntaRequest novaPergunta){
		
		if(!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Produto produto = produtoRepository.findById(id).get();
		
		Pergunta pergunta = novaPergunta.toPergunta(usuario, produto);
		
		perguntaRepository.save(pergunta);
		
		emails.mandarEmailPergunta(pergunta);
		
		return ResponseEntity.ok().build();
	}
	
}
