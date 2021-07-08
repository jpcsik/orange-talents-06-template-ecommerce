package br.com.zupacademy.jpcsik.mercadolivre.produto.imagem;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class NovaImagemRequest {

	@Size(min = 1)
	@NotNull
	private List<MultipartFile> imagem;
	
	public NovaImagemRequest(@Size(min = 1) @NotNull List<MultipartFile> imagem) {
		this.imagem = imagem;
	}

	public void setImagens(List<MultipartFile> imagem) {
		this.imagem = imagem;
	}

	public List<MultipartFile> getImagens() {
		return imagem;
	}
	
}
