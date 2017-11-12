package br.unisinos.desenvsoft3.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JWTBean {

	public static JWTBean empty() { 
		return new JWTBean();
	}
	
	private Integer idUsuario;
	private String role;
	private String user;
	
	private JWTBean() {
	}
	
	public JWTBean(String token, String secret, String tokenPrefix) {
		Jws<Claims> jws = Jwts.parser()
							  .setSigningKey(secret)
							  .parseClaimsJws(token.replace(tokenPrefix, ""));
		user = jws.getBody().getSubject();
		role = jws.getBody().get("role", String.class);
		idUsuario = jws.getBody().get("idUsuario", Integer.class);
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getUser() {
		return user;
	}
	
	public Authentication getAuthentication() {
		return user != null ? new UsernamePasswordAuthenticationToken(user, null, buildRolesList(role)) : null;
	}
	
	private List<GrantedAuthority> buildRolesList(String role) {
		if(role == null) {
			return Collections.emptyList();
		} else {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
			return Arrays.asList(authority);
		}
	}
}
