package br.unisinos.desenvsoft3.model.pedido.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.generic.dao.DataAccessManager;
import br.unisinos.desenvsoft3.model.generic.util.HQLBuilder;
import br.unisinos.desenvsoft3.model.pedido.domain.Pedido;

@Repository
public class PedidoDAO {

	@Autowired
	private DataAccessManager dataAccessManager;
	
	public void salvar(Pedido pedido) {
		dataAccessManager.entity(Pedido.class).save(pedido);
		dataAccessManager.flush();
	}
	
	public Pedido get(Integer idPedido) {
		return dataAccessManager.entity(Pedido.class).get(idPedido);
	}

	public Pedido getAutorizado(Integer idPedido, Integer idUsuario) {
		HQLBuilder hql = new HQLBuilder()
				.append(" SELECT pedi ")
				.append(" FROM Pedido pedi ")
				.append(" WHERE pedi.id = :idPedido ", idPedido)
				.append(" AND pedi.usuario.id = :idUsuario ", idUsuario);
		return dataAccessManager.query(hql.toString()).namedParameters(hql.namedParameters()).uniqueResult();
	}
}
