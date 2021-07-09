package br.com.zupacademy.jpcsik.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zupacademy.jpcsik.mercadolivre.categoria.Categoria;
import br.com.zupacademy.jpcsik.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.jpcsik.mercadolivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zupacademy.jpcsik.mercadolivre.produto.imagem.ImagemProduto;
import br.com.zupacademy.jpcsik.mercadolivre.produto.opiniao.Opiniao;
import br.com.zupacademy.jpcsik.mercadolivre.produto.pergunta.Pergunta;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@Min(1)
	private Integer quantidade;
	@NotNull
	@OneToMany(mappedBy = "produto", cascade=CascadeType.PERSIST)
	private List<Caracteristica> caracteristicas;
	@NotBlank
	@Size(max=1000)
	@Column(length = 1000)
	private String descricao;
	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;
	@NotNull
	@Valid
	@ManyToOne
	private Usuario proprietario;
	@NotNull
	@PastOrPresent
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@OneToMany(mappedBy = "produto", cascade=CascadeType.MERGE)
	private Set<ImagemProduto> imagemProduto;
	@OneToMany(mappedBy = "produto")
	private List<Pergunta> perguntas;
	@OneToMany(mappedBy = "produto")
	private List<Opiniao> opinioes;
	
	@Deprecated
	public Produto() {
	}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Min(1) Integer quantidade,
			@NotNull List<CaracteristicaRequest> caracteristicaRequest, 
			@NotBlank @Size(max = 1000) String descricao,
			@NotNull @Valid Categoria categoria, @NotNull @Valid Usuario proprietario) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicaRequest.stream().map(c -> c.toCaracteristica(this)).collect(Collectors.toList());
		this.descricao = descricao;
		this.categoria = categoria;
		this.proprietario = proprietario;
	}

	public void salvarImagens(Set<String> links) {
		this.imagemProduto = links.stream().map(link -> new ImagemProduto(link, this)).collect(Collectors.toSet());
		
	}

	public boolean pertenceAoProprietario(Usuario proprietario) {
		return this.proprietario.getId() == proprietario.getId();
	}

	public Usuario getProprietario() {
		return proprietario;
	}

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public <T> List <T> mapCaracteristicas(Function<Caracteristica, T> funcao) {
		return this.caracteristicas
				.stream()
				.map(funcao)
				.collect(Collectors.toList());
	}

	public <T> Set <T> mapImagens(Function<ImagemProduto, T> funcao) {
		return this.imagemProduto
				.stream()
				.map(funcao)
				.collect(Collectors.toSet());
	}

	public <T> List <T> mapPergunta(Function<Pergunta, T> funcao) {
		return this.perguntas
				.stream()
				.map(funcao)
				.collect(Collectors.toList());
	}

	public  <T> List <T> mapOpiniao(Function<Opiniao, T> funcao) {
		return this.opinioes
				.stream()
				.map(funcao)
				.collect(Collectors.toList());
	}

}
