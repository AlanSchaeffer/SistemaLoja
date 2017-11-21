package br.unisinos.desenvsoft3.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ActiveSessionsMap {

	private Map<String, SessionAttributes> sessions = new HashMap<>();
	
	public void registrar(String JWT, Integer idUsuario, String role) {
		SessionAttributes attr = new SessionAttributes();
		attr.idUsuario = idUsuario;
		attr.role = role;
		sessions.put(JWT, attr);
	}
	
	public Integer getIdUsuario(String JWT) {
		SessionAttributes attr = sessions.get(JWT);
		
		if(attr == null) {
			throw new IllegalArgumentException("Sessão inválida.");
		} else {
			return attr.idUsuario;
		}
	}
	
	public String getRole(String JWT) {
		SessionAttributes attr = sessions.get(JWT);
		
		if(attr == null) {
			throw new IllegalArgumentException("Sessão inválida.");
		} else {
			return attr.role;
		}
	}
	
	private static class SessionAttributes {
		private Integer idUsuario;
		private String role;
	}
}
