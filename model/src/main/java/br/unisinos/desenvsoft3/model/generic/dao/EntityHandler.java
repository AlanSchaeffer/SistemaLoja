package br.unisinos.desenvsoft3.model.generic.dao;

import java.io.Serializable;
import java.util.List;

public interface EntityHandler<T> {

	public void save(T bean);
	public void delete(T bean);
	public List<T> list();
	public T get(Serializable id);
}
