package br.unisinos.desenvsoft3.model.pedido.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.generic.dao.DataAccessManager;
import br.unisinos.desenvsoft3.model.generic.util.HQLBuilder;

@Repository
public class ListagemPedidosRepository {

	@Autowired
	private DataAccessManager dataAccessManager;
	
	public List<PedidoListadoView> listarPedidosUsuario(Integer idUsuario) {
		HQLBuilder hql = new HQLBuilder()
				.append(" SELECT pedi.id as id ")
				.append("		,SUM(item.valor) as valorTotal ")
				.append("		,CAST(COUNT(item.id) as int) as qtdeItens ")
				.append("		,pedi.dtPedido as dtPedido ")
				.append(" FROM Pedido pedi ")
				.append(" INNER JOIN pedi.itens item ")
				.append(" WHERE pedi.usuario.id = :idUsuario ", idUsuario)
				.append(" GROUP BY pedi.id, pedi.dtPedido, item.id, item.valor ")
				.append(" ORDER BY pedi.dtPedido DESC ");
		return dataAccessManager.query(hql.toString())
								.namedParameters(hql.namedParameters())
								.find(PedidoListadoView.class);
	}
	
	public List<PedidoListadoAdminView> listarPedidosAbertosParaAdministrador() {
		HQLBuilder hql = new HQLBuilder()
				.append(" SELECT pedi.id as id ")
				.append("		,SUM(item.valor) as valorTotal ")
				.append("		,CAST(COUNT(item.id) as int) as qtdeItens ")
				.append("		,pedi.dtPedido as dtPedido ")
				.append("		,usua.nome as nmUsuario ")
				.append(" FROM Pedido pedi ")
				.append(" INNER JOIN pedi.itens item ")
				.append(" INNER JOIN pedi.usuario usua ")
				.append(" WHERE pedi.statusPedido IN ('C','F') ")
				.append(" GROUP BY pedi.id, pedi.dtPedido, item.id, item.valor, usua.nome ")
				.append(" ORDER BY pedi.dtPedido DESC ");
		return dataAccessManager.query(hql.toString())
								.find(PedidoListadoAdminView.class);
	}
}
