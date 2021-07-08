package br.com.zupacademy.jpcsik.mercadolivre.produto.imagem;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {

	Set<String> salvar(List<MultipartFile> imagens);

}
