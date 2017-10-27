package br.unisinos.desenvsoft3.model.generic.dao;

import org.hibernate.Criteria;

public interface DataAccessManager {

	<T> EntityHandler<T> entity(Class<T> type);
	QueryExecutor query(String hql);
	
	<T> Criteria criteria(Class<T> clazz);
	void flush();
}
