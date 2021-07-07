package br.com.zupacademy.jpcsik.mercadolivre.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zupacademy.jpcsik.mercadolivre.security.token.GeradorToken;
import br.com.zupacademy.jpcsik.mercadolivre.usuario.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private Autenticacao autenticacao;
	
	@Autowired
	private GeradorToken geradorToken;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacao).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/h2-console/**")
					.permitAll()
				.antMatchers(HttpMethod.POST, "/usuario/cadastrar")
					.permitAll()
				.antMatchers(HttpMethod.POST, "/token")
					.permitAll()
				.antMatchers(HttpMethod.POST, "/categoria/cadastrar")
					.permitAll()
			.anyRequest()
			.authenticated()
				.and()
			.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.addFilterBefore(new AutenticacaoViaTokenFilter(geradorToken, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
}