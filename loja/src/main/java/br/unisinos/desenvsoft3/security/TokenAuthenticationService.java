package br.unisinos.desenvsoft3.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	static final String SECRET = "ToEveryone";
	static final String TOKEN_PREFIX = "Rasputin";
	static final String HEADER_STRING = "Authorization";

	static String addAuthentication(HttpServletResponse res, String username) {
		String JWT = Jwts.builder()
						 .setSubject(username)
						 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
						 .signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		return TOKEN_PREFIX + " " + JWT;
	}

	static JWTBean getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			return new JWTBean(token, SECRET, TOKEN_PREFIX);
		}
		return JWTBean.empty();
	}
}