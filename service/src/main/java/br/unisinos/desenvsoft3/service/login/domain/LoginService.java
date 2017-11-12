package br.unisinos.desenvsoft3.service.login.domain;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.login.dao.UsuarioDAO;
import br.unisinos.desenvsoft3.model.login.domain.Usuario;

@Service
public class LoginService implements UserDetailsService {

	@Resource
	private UsuarioDAO usuarioDAO;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDAO.getUsuario(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		} else {
			return buildUserForAuthentication(usuario);
		}
	}

	private User buildUserForAuthentication(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		return new User(usuario.getNome(), usuario.getSenha(), true, true, true, true, authorities);
	}

	@Transactional
	public Integer getIdUsuarioByName(String usernameOrEmail) {
		Usuario usuario = usuarioDAO.getUsuario(usernameOrEmail);
		
		if(usuario == null) {
			throw new IllegalArgumentException("Usuário inexistente");
		} else {
			return usuario.getId();
		}
	}
}
