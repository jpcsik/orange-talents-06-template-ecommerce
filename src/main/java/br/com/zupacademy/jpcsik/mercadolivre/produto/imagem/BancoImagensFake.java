package br.com.zupacademy.jpcsik.mercadolivre.produto.imagem;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class BancoImagensFake implements Uploader{

	public Set<String> salvar(@Valid List<MultipartFile> listaImagens) {
		return listaImagens
				.stream()
				.map(imagem -> "http://link-fake/"+imagem.getOriginalFilename()+UUID.randomUUID().toString())
				.collect(Collectors.toSet());
		
	}

}
