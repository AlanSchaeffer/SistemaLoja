package br.unisinos.desenvsoft3.model.login.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.generic.dao.DataAccessManager;
import br.unisinos.desenvsoft3.model.login.domain.Usuario;

@Repository
public class UsuarioDAO {

	@Resource
	private EntityManager entityManager;
	
	@Resource
	private DataAccessManager dataAccessManager;
	
	public Integer conta() {
		return ((Number) entityManager.createQuery(" SELECT COUNT(*) FROM Usuario ").getSingleResult()).intValue();
	}
	
	public List<String> listarNomes() {
		return dataAccessManager.findAll(Usuario.class).stream().map(Usuario::getName).collect(Collectors.toList());
	}

	@Transactional
	public void save(Usuario usuario) {
		dataAccessManager.save(usuario);
		dataAccessManager.flush();
	}
}
