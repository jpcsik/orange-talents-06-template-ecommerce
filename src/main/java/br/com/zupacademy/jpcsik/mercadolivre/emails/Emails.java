package br.com.zupacademy.jpcsik.mercadolivre.emails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zupacademy.jpcsik.mercadolivre.produto.pergunta.Pergunta;

@Service
public class Emails {

	@Autowired
	private Mailer mailer;
	
	public void mandarEmailPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send( 
				"Nova Pergunta!",
				pergunta.getTitulo(),
				"novapergunta@nossomercadolivre.com", 
				pergunta.getUsuario().getEmail(),
				pergunta.getProduto().getProprietario().getEmail());
	}

}
