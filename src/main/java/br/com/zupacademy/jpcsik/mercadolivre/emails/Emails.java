package br.com.zupacademy.jpcsik.mercadolivre.emails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zupacademy.jpcsik.mercadolivre.compra.Compra;
import br.com.zupacademy.jpcsik.mercadolivre.produto.pergunta.Pergunta;

@Service
public class Emails {

	@Autowired
	private Mailer mailer;
	
	public void mandarEmailNovaPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send( 
				"Nova Pergunta!",
				pergunta.getTitulo(),
				"novapergunta@nossomercadolivre.com", 
				pergunta.getUsuario().getEmail(),
				pergunta.getProduto().getProprietario().getEmail());
	}
	
	public void mandarEmailNovaCompra(@NotNull @Valid Compra compra) {
		mailer.send( 
				"Nova Compra!",
				compra.getProduto().getNome(),
				"novacompra@nossomercadolivre.com", 
				compra.getComprador().getEmail(),
				compra.getProduto().getProprietario().getEmail());
	}

	public void mandarEmailCompraFizalizada(Compra compra) {
		mailer.send("Compra finalizada com Sucesso!",
				"Produto: " + compra.getProduto().getNome()+
				", Descricao: "+compra.getProduto().getDescricao()+
				", Preco:"+compra.getProduto().getValor(),
				"compra@nossomercadolivre.com",
				compra.getProduto().getProprietario().getEmail(),
				compra.getComprador().getEmail());
		
	}

	public void mandarEmailCompraRejeitada(Compra compra) {
		mailer.send("Compra rejeitada!",
				"Tente novamente: http://localhost:8080/comprar",
				"compra@nossomercadolivre.com",
				compra.getProduto().getProprietario().getEmail(),
				compra.getComprador().getEmail());
	}

}
