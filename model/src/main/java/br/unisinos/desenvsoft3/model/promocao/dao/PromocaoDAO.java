package br.unisinos.desenvsoft3.model.promocao.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.generic.dao.DataAccessManager;
import br.unisinos.desenvsoft3.model.promocao.domain.Promocao;

@Repository
public class PromocaoDAO {

	@Autowired
	private DataAccessManager dataAccessManager;
	
	public void salvar(List<Promocao> promocoes) {
		promocoes.forEach(dataAccessManager.entity(Promocao.class)::save);
		dataAccessManager.flush();
	}

	public Promocao get(Integer idPromocao) {
		return dataAccessManager.entity(Promocao.class).get(idPromocao);
	}

	public void deletarPromocoes(List<Integer> idsPromocoes) {
		idsPromocoes.stream()
					.map(this::get)
					.forEach(dataAccessManager.entity(Promocao.class)::delete);
		dataAccessManager.flush();
	}
}
