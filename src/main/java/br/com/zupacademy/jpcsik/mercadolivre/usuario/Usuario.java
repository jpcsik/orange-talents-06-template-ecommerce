package br.com.zupacademy.jpcsik.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.mindrot.jbcrypt.BCrypt;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@PastOrPresent
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@Email
	@NotBlank
	private String email;
	@NotBlank
	@Size(min=6)
	private String senha;

	@Deprecated
	public Usuario() {
	}


	public Usuario(@NotBlank String email, @NotBlank @Size(min = 6) String senha) {
		this.email = email;
		this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
	}
	
}
