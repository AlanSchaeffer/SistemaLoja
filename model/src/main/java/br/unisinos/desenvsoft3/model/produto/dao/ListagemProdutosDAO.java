package br.unisinos.desenvsoft3.model.produto.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.generic.dao.DataAccessManager;
import br.unisinos.desenvsoft3.model.generic.util.HQLBuilder;
import br.unisinos.desenvsoft3.model.produto.domain.ProdutoFilter;
import br.unisinos.desenvsoft3.model.produto.domain.ProdutoListado;
import br.unisinos.desenvsoft3.model.produto.domain.ProdutoView;

@Repository
public class ListagemProdutosDAO {

	@Autowired
	private DataAccessManager dataAccessManager;
	
	public List<ProdutoListado> pesquisar(ProdutoFilter filtro) {
		HQLBuilder hql = new HQLBuilder()
				.append(" SELECT prod.id as id ")
				.append("		,prod.nome as nome ")
				.append("		,prod.preco as preco ")
				.append("		,prod.estoque as estoque ")
				.append("		,(CASE WHEN prod.estoque = 0 THEN false ELSE true END) as temEstoque ")
				.append(" FROM Produto prod ")
				.append(" WHERE 1=1 ")
				.appendNullable(" AND prod.nome LIKE :nome ", filtro.getNomeFilterWithWildcards())
				.append(" ORDER BY prod.nome ");
		
		return dataAccessManager.query(hql.toString())
								.namedParameters(hql.namedParameters())
								.find(ProdutoListado.class);
	}
	
	public ProdutoView visualizar(Integer id) {
		HQLBuilder hql = new HQLBuilder()
				.append(" SELECT prod.id as id ")
				.append("		,prod.nome as nome ")
				.append("		,prod.descricao as descricao ")
				.append("		,prod.preco as preco ")
				.append("		,prod.estoque as estoque ")
				.append(" FROM Produto prod ")
				.append(" WHERE prod.id = :id ", id);
		
		return dataAccessManager.query(hql.toString())
								.namedParameters(hql.namedParameters())
								.uniqueResult(ProdutoView.class);
	}
}
