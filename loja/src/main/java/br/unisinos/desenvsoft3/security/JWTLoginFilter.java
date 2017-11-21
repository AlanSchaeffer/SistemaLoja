package br.unisinos.desenvsoft3.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.unisinos.desenvsoft3.service.login.domain.LoginRequest;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private boolean obrigaAdministrador = false;
	private JWTLoginHelperService jwtLoginHelperService;

	public JWTLoginFilter(String url, AuthenticationManager authManager, JWTLoginHelperService jwtLoginHelperService) {
		this(url, authManager, jwtLoginHelperService, false);
	}
	
	public JWTLoginFilter(String url, AuthenticationManager authManager, JWTLoginHelperService jwtLoginHelperService, boolean obrigaAdministrador) {
		super(new AntPathRequestMatcher(url));
		this.jwtLoginHelperService = jwtLoginHelperService;
		setAuthenticationManager(authManager);
		this.obrigaAdministrador = obrigaAdministrador;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		LoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), LoginRequest.class);
		validaAdministrador(creds);
		
		List<GrantedAuthority> authorities = createAuthoritiesForUser(creds.getUsernameOrEmail());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(creds.getUsernameOrEmail(), creds.getPassword(), authorities);
		return getAuthenticationManager().authenticate(token);
	}
	
	private List<GrantedAuthority> createAuthoritiesForUser(String username) {
		if(jwtLoginHelperService.isUsuarioAdministrador(username)) {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			return Collections.emptyList();
		}
	}
	
	private void validaAdministrador(LoginRequest creds) {
		if(obrigaAdministrador) {
			if(!jwtLoginHelperService.isUsuarioAdministrador(creds.getUsernameOrEmail())) {
				throw new RuntimeException("Usu�rio inv�lido.");
			}
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String role = null;
		Integer idUsuario = jwtLoginHelperService.getIdUsuario(auth.getName());
		if(!auth.getAuthorities().isEmpty()) {
			role = auth.getAuthorities().iterator().next().getAuthority();
		}
		
		String token = TokenAuthenticationService.addAuthentication(res, auth.getName());
		jwtLoginHelperService.registrarSessao(token, idUsuario, role);
	}
}