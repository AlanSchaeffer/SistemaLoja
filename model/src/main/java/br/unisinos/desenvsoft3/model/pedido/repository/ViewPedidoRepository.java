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
				.append("		,SUM(peit.valor * peit.quantidade) as valor ")
				.append("		,CAST(COUNT(peit.quantidade) as int) as quantidade ")
				.append(" FROM PedidoItem peit ")
				.append(" INNER JOIN peit.pedido pedi ")				
				.append(" INNER JOIN pedi.usuario usua ")
				.append(" WHERE pedi.id = :idPedido ", idPedido)
				.append(" GROUP BY pedi.id, pedi.dtPedido, pedi.vlFrete, pedi.statusPedido, pedi.enderecoEntrega, usua.nome ")				
				.appendNullable(" AND usua.id = :idUsuario ", idUsuario);
		
		return dataAccessManager.query(hql.toString())
								.namedParameters(hql.namedParameters())
								.find(FlatPedidoResult.class);
	}
}
