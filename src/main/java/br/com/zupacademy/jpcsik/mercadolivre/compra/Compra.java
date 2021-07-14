package br.com.zupacademy.jpcsik.mercadolivre.compra;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.jpcsik.mercadolivre.compra.fechamento.RetornoPagamento;
import br.com.zupacademy.jpcsik.mercadolivre.compra.fechamento.Transacao;
import br.com.zupacademy.jpcsik.mercadolivre.produto.Produto;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne
	private Produto produto;
	@NotNull
	@Positive
	private Integer quantidade;
	@NotNull
	@ManyToOne
	private Usuario comprador;
	@Enumerated
	@NotNull
	private GatewayPagamento gateway;
	@Enumerated
	@NotNull
	private StatusCompra statusCompra;
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private List<Transacao> transacoes;
	
	@Deprecated
	public Compra() {
	}

	public Compra(@NotNull Produto produto, @NotNull @Positive Integer quantidade, @NotNull Usuario comprador,
			@NotNull GatewayPagamento gateway, @NotNull StatusCompra statusCompra) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.gateway = gateway;
		this.statusCompra = statusCompra;
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public Usuario getComprador() {
		return comprador;
	}

	public ResponseEntity<?> novaUrlPagamento(UriComponentsBuilder uriBuilder) {
		String urlPagamento = this.gateway.criaUrlPagamento(uriBuilder, this);
		return ResponseEntity.status(302).body(urlPagamento);
	}
	
	public List<Transacao> transacoesComcluidasComSucesso() {
		List<Transacao> transacoesComSucesso = 
				this.transacoes.stream().filter(Transacao :: concluidaComSucesso).collect(Collectors.toList());
		return transacoesComSucesso;
	}
	
	public void adicionaTransacao(@Valid RetornoPagamento retorno) {
		Transacao novaTransacao = retorno.toTransacao(this);

		Assert.isTrue(!this.transacoes.contains(novaTransacao), "Transação já existe!");
		Assert.isTrue(transacoesComcluidasComSucesso().isEmpty(), "Transação já foi concluida com sucesso!");
		
		this.transacoes.add(novaTransacao);
	}
	
	public Boolean finalizada() {
		return !transacoesComcluidasComSucesso().isEmpty();
	}

}
