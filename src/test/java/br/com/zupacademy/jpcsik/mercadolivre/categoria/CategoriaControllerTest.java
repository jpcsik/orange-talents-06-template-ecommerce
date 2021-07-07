package br.com.zupacademy.jpcsik.mercadolivre.categoria;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	CategoriaRepository categoriaRepository;

	@Test
	@Transactional
	public void deveCadastrarCategoriaSemCategoriaMaeStatus200() throws Exception {
		URI uri = new URI("/categoria/cadastrar");
		String json = "{\"nome\":\"Tecnologia\",\"idCategoriaMae\":\"\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
		
	}
	
	@Test
	@Transactional
	public void deveCadastrarCategoriaComCategoriaMaeExistenteStatus200() throws Exception {
		
		Categoria categoria = new Categoria("Tecnologia");
		categoriaRepository.save(categoria);
		
		
		URI uri = new URI("/categoria/cadastrar");
		String json = "{\"nome\":\"Computador\",\"idCategoriaMae\":\""+categoria.getId()+"\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
		
		categoriaRepository.deleteAll();
	}
	
	@Test
	@Transactional
	public void naoDeveCadastrarCategoriaComCategoriaMaeInexistenteStatus400() throws Exception {
		
		URI uri = new URI("/categoria/cadastrar");
		String json = "{\"nome\":\"Nao existe\",\"idCategoriaMae\":\"9999\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
	
	@Test
	@Transactional
	public void naoDeveCadastrarCategoriaComNomeNuloStatus400() throws Exception {
		
		URI uri = new URI("/categoria/cadastrar");
		String json = "{\"nome\":\"\",\"idCategoriaMae\":\"\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
	
	@Test
	@Transactional
	public void naoDeveCadastrarCategoriaRepetidaStatus400() throws Exception {
		
		Categoria categoria = new Categoria("Repetida");
		categoriaRepository.save(categoria);
		
		URI uri = new URI("/categoria/cadastrar");
		String json = "{\"nome\":\"Repetida\",\"idCategoriaMae\":\"\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
		
		categoriaRepository.deleteAll();
	}

}
