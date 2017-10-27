package br.unisinos.desenvsoft3.model.generic.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

class HibernateEntityHandler<T> implements EntityHandler<T> {

	private final Session session;
	private final Class<T> type;

	HibernateEntityHandler(Session session, Class<T> type) {
		this.session = session;
		this.type = type;
	}
	
	@Override
	public void save(T bean) {
		session.saveOrUpdate(bean);
	}

	@Override
	public void delete(T bean) {
		session.delete(bean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list() {
		return session.createCriteria(type).list();
	}

	@Override
	public T load(Serializable id) {
		return session.load(type, id);
	}
}
