package br.unisinos.desenvsoft3.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	static final String SECRET = "ToEveryone";
	static final String TOKEN_PREFIX = "Rasputin";
	static final String HEADER_STRING = "Authorization";

	static void addAuthentication(HttpServletResponse res, String username, String role) {
		String JWT = Jwts.builder()
						 .setSubject(username)
						 .claim("role", role)
						 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
						 .signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			Jws<Claims> jws = Jwts.parser()
								  .setSigningKey(SECRET)
								  .parseClaimsJws(token.replace(TOKEN_PREFIX, ""));
			String user = jws.getBody()
							 .getSubject();
			String role = jws.getBody().get("role", String.class);
			
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, buildRolesList(role)) : null;
		}
		return null;
	}
	
	private static List<GrantedAuthority> buildRolesList(String role) {
		if(role == null) {
			return Collections.emptyList();
		} else {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
			return Arrays.asList(authority);
		}
	}
}