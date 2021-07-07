package br.com.zupacademy.jpcsik.mercadolivre.security.token;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private GeradorToken geradorToken;

	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid NovoTokenRequest novoToken) {
		
		UsernamePasswordAuthenticationToken dadosLogin = novoToken.converter();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = geradorToken.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body("Dados Incorretos!");
		}
	}
	
}
