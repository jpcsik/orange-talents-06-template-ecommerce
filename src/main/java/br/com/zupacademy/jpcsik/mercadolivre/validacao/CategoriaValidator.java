package br.com.zupacademy.jpcsik.mercadolivre.validacao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.jpcsik.mercadolivre.categoria.Categoria;
import br.com.zupacademy.jpcsik.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.jpcsik.mercadolivre.categoria.NovaCategoriaRequest;

@Component
public class CategoriaValidator implements Validator{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCategoriaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		NovaCategoriaRequest request = (NovaCategoriaRequest) target;
		
		if(request.getIdCategoriaMae() == null) {
			return;
		}
		
		Optional<Categoria> categoria = categoriaRepository.findById(request.getIdCategoriaMae());		
		
		if(categoria.isEmpty()) {
			errors.rejectValue("idCategoriaMae", null, "Categoria não cadastrada!");
		}
		
	
	}

}