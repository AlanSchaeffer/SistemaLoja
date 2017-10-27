package br.unisinos.desenvsoft3.model.generic.dao;

import java.util.List;
import java.util.Map;

public interface QueryExecutor {

	QueryExecutor parameters(Object... params);
	QueryExecutor namedParameters(Map<String, Object> params);
	
	<T> List<T> find();
	<T> T uniqueResult();
	<T> List<T> find(Class<T> resultClass);
	<T> T uniqueResult(Class<T> resultClass);
}
