package br.unisinos.desenvsoft3.model.login.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO {

	@Resource
	private EntityManager entityManager;
	
	public Integer conta() {
		return ((Number) entityManager.createQuery(" SELECT COUNT(*) FROM Usuario ").getSingleResult()).intValue();
	}
}
