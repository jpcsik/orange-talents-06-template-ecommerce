package br.com.zupacademy.jpcsik.mercadolivre.compra.fechamento;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import br.com.zupacademy.jpcsik.mercadolivre.compra.Compra;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Enumerated
	private StatusTransacao status;
	@NotBlank
	@Column(unique = true)
	private String idTransacao;
	@NotNull
	@PastOrPresent
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@NotNull
	@Valid
	@ManyToOne
	private Compra compra;
	
	@Deprecated
	public Transacao() {
	}

	public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacao, @NotNull @Valid Compra compra) {
		this.status = status;
		this.idTransacao = idTransacao;
		this.compra = compra;
	}
	
	public Boolean concluidaComSucesso() {
		if(this.status.equals(StatusTransacao.SUCESSO)) {
			return true;
		}return false;
	}

}
