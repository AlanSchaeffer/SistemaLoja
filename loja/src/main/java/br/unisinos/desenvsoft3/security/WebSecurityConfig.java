package br.unisinos.desenvsoft3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.unisinos.desenvsoft3.service.login.domain.LoginService;
import br.unisinos.desenvsoft3.sistema.login.UsuarioBean;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ContaAdministrativa contaAdministrativa;
	
	@Autowired
	private JWTLoginHelperService jwtLoginHelperService;
	
	@Autowired
	private UsuarioBean usuarioBean;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				   .authorizeRequests()
				   		.antMatchers("/services/produtos/salvar").hasRole("ADMIN")
				   		.antMatchers("/services/carrinho/", "/services/carrinho/*", "/services/carrinho/**").authenticated()
				   		.antMatchers("/services/pedidos/**").authenticated()
				   		.anyRequest().permitAll()
				   .and()
				// We filter the api/login requests
				   .addFilterBefore(new JWTLoginFilter("/services/usuario/login", authenticationManager(), jwtLoginHelperService), UsernamePasswordAuthenticationFilter.class)
				   .addFilterBefore(new JWTLoginFilter("/services/admin/login", authenticationManager(), jwtLoginHelperService, true), UsernamePasswordAuthenticationFilter.class)
				// And filter other requests to check the presence of JWT in header
				   .addFilterBefore(new JWTAuthenticationFilter(usuarioBean), UsernamePasswordAuthenticationFilter.class)
				   .addFilter(new RestConfig().corsFilter());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		userAuthentication(auth);
		adminAuthentication(auth);
	}
	
	private void adminAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		contaAdministrativa.configure(auth);
	}
	
	private void userAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
}