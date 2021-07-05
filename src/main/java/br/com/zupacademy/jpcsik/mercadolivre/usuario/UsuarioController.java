package br.com.zupacademy.jpcsik.mercadolivre.usuario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/usuario/cadastrar")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoUsuarioRequest novoUsuario){
		usuarioRepository.save(novoUsuario.toUsuario());
		return ResponseEntity.ok().build();
		
	}

}
