package br.com.zupacademy.jpcsik.mercadolivre.categoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.zupacademy.jpcsik.mercadolivre.validacao.ValorUnico;

public class NovaCategoriaRequest {

	@NotBlank
	@ValorUnico(campo = "nome", classeDeDominio = Categoria.class)
	private String nome;
	@Positive
	private Long idCategoriaMae;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public NovaCategoriaRequest(@NotBlank String nome, @Positive Long idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}
	
	public Categoria toCategoria(CategoriaRepository categoriaRepository) {
		if(this.idCategoriaMae != null) {
		Categoria mae = categoriaRepository.findById(idCategoriaMae).get();
		Categoria categoria = new Categoria(
				this.nome,
				mae);
		return categoria;
		}
		Categoria categoria = new Categoria(this.nome);
		return categoria;
		
	}
	
	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}

}
