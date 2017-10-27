package br.unisinos.desenvsoft3.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				   .authorizeRequests()
				   .anyRequest().permitAll()
				   .and()
				// We filter the api/login requests
				   .addFilterBefore(new JWTLoginFilter("/services/usuario/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				// And filter other requests to check the presence of JWT in header
				   .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		adminAuthentication(auth);
		userAuthentication(auth);
	}
	

	private void adminAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		new InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>()
			.withUser("administraSys")
			.password("sysloja999")
			.roles("ADMIN")
			.and()
			.configure(auth);
	}
	
	private void userAuthentication(AuthenticationManagerBuilder auth) {
		
	}
}