package br.unisinos.desenvsoft3.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JWTBean {

	public static JWTBean empty() { 
		return new JWTBean();
	}
	
	private Integer idUsuario;
	private String user;
	private String token;
	
	private JWTBean() {
	}
	
	public JWTBean(String token, String secret, String tokenPrefix) {
		this.token = token;
		Jws<Claims> jws = Jwts.parser()
							  .setSigningKey(secret)
							  .parseClaimsJws(token.replace(tokenPrefix, ""));
		user = jws.getBody().getSubject();
		idUsuario = jws.getBody().get("idUsuario", Integer.class);
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getToken() {
		return token;
	}
	
	public Authentication getAuthentication(JWTLoginHelperService jwtLoginHelperService) {
		return user != null ? new UsernamePasswordAuthenticationToken(user, null, jwtLoginHelperService.buildRolesListForLoginToken(token)) : null;
	}
	
	public void preencheUsuarioBean(JWTLoginHelperService jwtLoginHelperService) {
		if(user != null) jwtLoginHelperService.preencheUsuarioBean(token);
	}
}
