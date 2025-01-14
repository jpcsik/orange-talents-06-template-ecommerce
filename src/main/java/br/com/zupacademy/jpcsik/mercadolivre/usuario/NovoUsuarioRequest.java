package br.com.zupacademy.jpcsik.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.jpcsik.mercadolivre.validacao.ValorUnico;

public class NovoUsuarioRequest {

	@Email
	@NotBlank
	@ValorUnico(campo = "email", classeDeDominio = Usuario.class)
	private String email;
	@NotBlank
	@Size(min=6)
	private String senha;
	
	@JsonCreator
	public NovoUsuarioRequest(@NotBlank String email, @NotBlank @Size(min = 6) String senha) {
		this.email = email;
		this.senha = senha;
	}

	public Usuario toUsuario() {
		Usuario usuario = new Usuario(
				this.email,
				this.senha
				);
		return usuario;
	}
	
}
