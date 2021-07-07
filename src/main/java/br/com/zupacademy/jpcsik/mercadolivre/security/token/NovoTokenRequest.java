package br.com.zupacademy.jpcsik.mercadolivre.security.token;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class NovoTokenRequest {

	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Size(min=6)
	private String senha;
	
	public NovoTokenRequest(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}
