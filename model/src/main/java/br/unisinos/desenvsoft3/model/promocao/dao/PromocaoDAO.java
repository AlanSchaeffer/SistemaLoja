package br.unisinos.desenvsoft3.model.promocao.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.generic.dao.DataAccessManager;
import br.unisinos.desenvsoft3.model.generic.util.HQLBuilder;
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

	public void deletar(Promocao promocao) {
		dataAccessManager.entity(Promocao.class).delete(promocao);
		dataAccessManager.flush();
	}
	
	public List<Promocao> getPromocoesDoProdutoNaData(Integer idProduto, Date data) {
		HQLBuilder hql = new HQLBuilder()
				.append(" SELECT prom ")
				.append(" FROM Promocao prom ")
				.append(" WHERE prom.produto.id = :idProduto ", idProduto)
				.append(" AND prom.dtInicial <= :data ", data)
				.append(" AND prom.dtFinal >= :data ")
				.append(" ORDER BY prom.dtInicial DESC ");
		return dataAccessManager.query(hql.toString())
								.namedParameters(hql.namedParameters())
								.find();
	}
}
