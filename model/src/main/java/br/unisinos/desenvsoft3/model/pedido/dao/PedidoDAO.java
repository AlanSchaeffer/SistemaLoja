package br.unisinos.desenvsoft3.model.pedido.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.generic.dao.DataAccessManager;
import br.unisinos.desenvsoft3.model.pedido.domain.Pedido;

@Repository
public class PedidoDAO {

	@Autowired
	private DataAccessManager dataAccessManager;
	
	public void salvar(Pedido pedido) {
		dataAccessManager.entity(Pedido.class).save(pedido);
		dataAccessManager.flush();
	}
	
}
