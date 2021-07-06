package br.com.zupacademy.jpcsik.mercadolivre.categoria;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.jpcsik.mercadolivre.validacao.CategoriaValidator;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaValidator categoriaValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(categoriaValidator);
	}
	
	@PostMapping("categoria/cadastrar")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaCategoriaRequest novaCategoria) {
		categoriaRepository.save(novaCategoria.toCategoria(categoriaRepository));
		return ResponseEntity.ok().build();
		
	}
	
}
