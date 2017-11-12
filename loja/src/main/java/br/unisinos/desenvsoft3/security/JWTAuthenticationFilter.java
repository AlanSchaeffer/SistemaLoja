package br.unisinos.desenvsoft3.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import br.unisinos.desenvsoft3.sistema.login.UsuarioBean;

public class JWTAuthenticationFilter extends GenericFilterBean {

	private UsuarioBean usuarioBean;
	
	public JWTAuthenticationFilter(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		JWTBean jwtBean = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);
		Authentication authentication = jwtBean.getAuthentication();
		usuarioBean.setIdUsuario(jwtBean.getIdUsuario());

		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
}