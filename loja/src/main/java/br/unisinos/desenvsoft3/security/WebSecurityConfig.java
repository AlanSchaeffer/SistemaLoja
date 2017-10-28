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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginService loginService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				   .authorizeRequests()
				   		.anyRequest().permitAll()
				   .and()
				// We filter the api/login requests
				   .addFilterBefore(new JWTLoginFilter("/services/usuario/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				// And filter other requests to check the presence of JWT in header
				   .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				   .addFilter(new RestConfig().corsFilter());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		userAuthentication(auth);
		adminAuthentication(auth);
	}
	
	// TODO criar user details service unificado, que consiga tambem responder o papel do usuario logado ao autenticador jwt

	private void adminAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("administraSys")
			.password("sysloja999")
			.roles("ADMIN");
	}
	
	private void userAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
}