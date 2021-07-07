package br.com.zupacademy.jpcsik.mercadolivre.usuario;

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
class UsuarioControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Test
	@Transactional
	public void deveCadastrarUsuarioStatus200() throws Exception {
		URI uri = new URI("/usuario/cadastrar");
		String json = "{\"email\":\"joao@email.com\",\"senha\":\"123456\"}";
		
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
	public void naoDeveCadastrarUsuarioComSenhaIncorretaStatus400() throws Exception {
		URI uri = new URI("/usuario/cadastrar");
		String json = "{\"email\":\"joao@email.com\",\"senha\":\"12345\"}";
		
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
	public void naoDeveCadastrarUsuarioComEmailNuloStatus400() throws Exception {
		URI uri = new URI("/usuario/cadastrar");
		String json = "{\"email\":\"\",\"senha\":\"12345\"}";
		
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
	public void naoDeveCadastrarUsuarioComSenhaNulaStatus400() throws Exception {
		URI uri = new URI("/usuario/cadastrar");
		String json = "{\"email\":\"joao@email.com\",\"senha\":\"\"}";
		
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
	public void naoDeveCadastrarUsuarioComEmailIncorretoStatus400() throws Exception {
		URI uri = new URI("/usuario/cadastrar");
		String json = "{\"email\":\"joaoemail.com\",\"senha\":\"123456\"}";
		
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
	public void naoDeveCadastrarUsuarioRepetidoStatus400() throws Exception {
		
		Usuario usuario = new Usuario("joao@email.com", "123456");
		usuarioRepository.save(usuario);
		
		
		URI uri = new URI("/usuario/cadastrar");
		String json = "{\"email\":\"joao@email.com\",\"senha\":\"123456\"}";
		
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
