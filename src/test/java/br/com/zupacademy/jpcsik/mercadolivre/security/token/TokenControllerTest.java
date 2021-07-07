package br.com.zupacademy.jpcsik.mercadolivre.security.token;

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

import br.com.zupacademy.jpcsik.mercadolivre.usuario.Usuario;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.UsuarioRepository;

@SpringBootTest
@AutoConfigureMockMvc
class TokenControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Test
	@Transactional
	public void deveGerarTokenParaUsuarioCadastradoStatus200() throws Exception {
		
		Usuario usuario = new Usuario("joao@email.com", "123456");
		usuarioRepository.save(usuario);
		
		
		URI uri = new URI("/token");
		String json = "{\"email\":\"joao@email.com\",\"senha\":\"123456\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
		
		usuarioRepository.deleteAll();
	}
	
	@Test
	@Transactional
	public void naoDeveGerarTokenParaUsuarioNaoCadastradoStatus400() throws Exception {
		
		URI uri = new URI("/token");
		String json = "{\"email\":\"naocadastrado@email.com\",\"senha\":\"123456\"}";
		
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
	public void naoDeveGerarTokenParaUsuarioComSenhaIncorretaStatus400() throws Exception {
		
		Usuario usuario = new Usuario("joao@email.com", "123456");
		usuarioRepository.save(usuario);
		
		URI uri = new URI("/token");
		String json = "{\"email\":\"joao@email.com\",\"senha\":\"1234567\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
		
		usuarioRepository.deleteAll();
	}
	
	@Test
	@Transactional
	public void naoDeveGerarTokenParaUsuarioComEmailIncorretoStatus400() throws Exception {
		
		Usuario usuario = new Usuario("joao@email.com", "123456");
		usuarioRepository.save(usuario);
		
		URI uri = new URI("/token");
		String json = "{\"email\":\"incorreto@email.com\",\"senha\":\"123456\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
	
}
