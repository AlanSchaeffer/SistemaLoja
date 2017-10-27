package br.unisinos.desenvsoft3.model.generic.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.login.domain.Usuario;

@Repository
public class HibernateDataAccessManager implements DataAccessManager {

	@Resource
	private EntityManager entityManager;
	
	private Session session() {
		return entityManager.unwrap(Session.class);
	}
	
	public <T> List<T> findAll(Class<T> clazz) {
		return session().createCriteria(clazz).list();
	}
	
	@Override
	public void save(Usuario usuario) {
		session().saveOrUpdate(usuario);
	}
	
	@Override
	public void flush() {
		entityManager.flush();
	}
}
