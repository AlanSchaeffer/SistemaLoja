package br.unisinos.desenvsoft3.model.pedido.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.generic.dao.DataAccessManager;
import br.unisinos.desenvsoft3.model.generic.util.HQLBuilder;

@Repository
public class ViewPedidoRepository {

	@Autowired
	private DataAccessManager dataAccessManager;
	
	public PedidoView getPedidoUsuario(Integer idPedido, Integer idUsuario) {
		if(idUsuario == null) {
			return new PedidoView(Collections.emptyList());
		}
		List<FlatPedidoResult> flatResults = consultarPedidos(idPedido, idUsuario);
		return new PedidoView(flatResults);
	}
	
	public PedidoView getPedidoAdministrador(Integer idPedido) {
		List<FlatPedidoResult> flatResults = consultarPedidos(idPedido, null);
		return new PedidoView(flatResults);
	}
	
	private List<FlatPedidoResult> consultarPedidos(Integer idPedido, Integer idUsuario) {
		HQLBuilder hql = new HQLBuilder()
				.append(" SELECT pedi.id as idPedido ")
				.append("		,pedi.dtPedido as dtPedido ")
				.append("		,pedi.vlFrete as vlFrete ")
				.append("		,pedi.statusPedido as statusPedido ")
				.append("		,pedi.enderecoEntrega as enderecoEntrega ")
				.append("		,usua.nome as nmUsuario ")
				.append("		,peit.id as idPedidoItem ")
				.append("		,prod.nmProduto as nmProduto ")
				.append("		,peit.valor as valor ")
				.append("		,peit.quantidade as quantidade ")
				.append(" FROM PedidoItem peit ")
				.append(" INNER JOIN peit.pedido pedi ")
				.append(" INNER JOIN peit.produto prod ")
				.append(" INNER JOIN pedi.usuario usua ")
				.append(" WHERE pedi.id = :idPedido ", idPedido)
				.appendNullable(" AND usua.id = :idUsuario ", idUsuario);
		
		return dataAccessManager.query(hql.toString())
								.namedParameters(hql.namedParameters())
								.find(FlatPedidoResult.class);
	}
}
