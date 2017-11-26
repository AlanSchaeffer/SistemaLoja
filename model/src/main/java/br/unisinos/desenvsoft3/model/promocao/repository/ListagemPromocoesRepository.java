package br.unisinos.desenvsoft3.model.promocao.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.generic.dao.DataAccessManager;
import br.unisinos.desenvsoft3.model.generic.util.HQLBuilder;

@Repository
public class ListagemPromocoesRepository {

	@Autowired
	private DataAccessManager dataAccessManager;
	
	public List<PromocaoView> listar() {
		HQLBuilder hql = new HQLBuilder()
				.append(" SELECT prom.id as id ")
				.append("		,prod.nome as nmProduto")
				.append("		,prom.dtInicial as dtInicial ")
				.append("		,prom.dtFinal as dtFinal")
				.append("		,prod.preco as vlProdutoOriginal ")
				.append("		,prom.peDesconto as peDesconto ")
				.append(" FROM Promocao prom ")
				.append(" INNER JOIN prom.produto prod ")
				.append(" ORDER BY prom.dtInicial ")
				.append("		  ,prod.nome ");
		return dataAccessManager.query(hql.toString())
								.namedParameters(hql.namedParameters())
								.find(PromocaoView.class);
	}
}
