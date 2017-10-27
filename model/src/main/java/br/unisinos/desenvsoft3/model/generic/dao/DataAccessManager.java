package br.unisinos.desenvsoft3.model.generic.dao;

import java.util.List;

import br.unisinos.desenvsoft3.model.login.domain.Usuario;

public interface DataAccessManager {

	<T> List<T> findAll(Class<T> clazz);
	void save(Usuario usuario);
	void flush();
}
