package br.unisinos.desenvsoft3.model.login.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.generic.dao.DataAccessManager;
import br.unisinos.desenvsoft3.model.generic.util.HQLBuilder;
import br.unisinos.desenvsoft3.model.login.domain.Usuario;

@Repository
public class UsuarioDAO {

	@Autowired
	private DataAccessManager dataAccessManager;
	
	public boolean existeComNomeOuEmail(String nome, String email) {
		HQLBuilder hql = new HQLBuilder()
				.append(" SELECT COUNT(*) ")
				.append(" FROM Usuario usuario ")
				.append(" WHERE usuario.nome = :nome ", nome)
				.append(" OR usuario.email = :email ", email);
		
		return dataAccessManager.query(hql.toString())
								.namedParameters(hql.namedParameters())
								.<Number>uniqueResult()
								.intValue() > 0;
	}

	@Transactional
	public void salvar(Usuario usuario) {
		dataAccessManager.entity(Usuario.class).save(usuario);
		dataAccessManager.flush();
	}
}
