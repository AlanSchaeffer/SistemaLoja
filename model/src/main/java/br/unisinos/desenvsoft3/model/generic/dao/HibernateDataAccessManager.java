package br.unisinos.desenvsoft3.model.generic.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDataAccessManager implements DataAccessManager {

	@Resource
	private EntityManager entityManager;
	
	private Session session() {
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	public <T> Criteria criteria(Class<T> clazz) {
		return session().createCriteria(clazz);
	}
	
	@Override
	public <T> EntityHandler<T> entity(Class<T> type) {
		return new HibernateEntityHandler<>(session(), type);
	}
	
	@Override
	public QueryExecutor query(String hql) {
		return new HibernateQueryExecutor(session(), hql);
	}
	
	@Override
	public void flush() {
		session().flush();
	}
}
