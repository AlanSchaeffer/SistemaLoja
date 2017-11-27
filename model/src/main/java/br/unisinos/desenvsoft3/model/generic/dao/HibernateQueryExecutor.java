package br.unisinos.desenvsoft3.model.generic.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;

@SuppressWarnings("unchecked")
class HibernateQueryExecutor implements QueryExecutor {

	private final Session session;
	private final String hql;
	
	private Object[] parameters;
	private Map<String, Object> namedParameters;
	
	HibernateQueryExecutor(Session session, String hql) {
		this.session = session;
		this.hql = hql;
	}
	
	@Override
	public QueryExecutor parameters(Object... params) {
		parameters = params;
		return this;
	}

	@Override
	public QueryExecutor namedParameters(Map<String, Object> params) {
		namedParameters = params;
		return this;
	}

	@Override
	public <T> List<T> find() {
		return createQuery().list();
	}

	@Override
	public <T> T uniqueResult() {
		return (T) createQuery().uniqueResult();
	}

	@Override
	public <T> List<T> find(Class<T> resultClass) {
		return createQuery().setResultTransformer(new AliasToBeanResultTransformer(resultClass)).list();
	}

	@Override
	public <T> T uniqueResult(Class<T> resultClass) {
		return (T) createQuery().setResultTransformer(new AliasToBeanResultTransformer(resultClass)).uniqueResult();
	}
	
	private Query createQuery() {
		Query query = session.createQuery(hql);
		setParameters(query);
		return query;
	}

	private void setParameters(Query query) {
		if(parameters != null) {
			int conta = 1;
			for (Object param : parameters) {
				query.setParameter(conta++, param);
			}
		} else if(namedParameters != null) {
			for (Entry<String, Object> entry : namedParameters.entrySet()) {
				if(entry.getValue() instanceof Collection) {
					query.setParameterList(entry.getKey(), (Collection<?>) entry.getValue());
				} else {
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
	}
}
