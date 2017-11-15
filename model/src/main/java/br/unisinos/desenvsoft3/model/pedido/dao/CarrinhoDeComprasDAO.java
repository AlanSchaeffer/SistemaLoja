package br.unisinos.desenvsoft3.model.pedido.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.generic.dao.DataAccessManager;
import br.unisinos.desenvsoft3.model.generic.util.HQLBuilder;
import br.unisinos.desenvsoft3.model.pedido.domain.CarrinhoDeCompras;

@Repository
public class CarrinhoDeComprasDAO {

	@Autowired
	private DataAccessManager dataAccessManager;
	
	public CarrinhoDeCompras getByUsuario(Integer idUsuario) {
		HQLBuilder hql = new HQLBuilder()
				.append(" SELECT cdco ")
				.append(" FROM CarrinhoDeCompras cdco ")
				.append(" WHERE cdco.usuario.id = :idUsuario ", idUsuario);
		
		return dataAccessManager.query(hql.toString())
								.namedParameters(hql.namedParameters())
								.uniqueResult();
	}

	public void save(CarrinhoDeCompras carrinho) {
		dataAccessManager.entity(CarrinhoDeCompras.class).save(carrinho);
		dataAccessManager.flush();
	}

	public void delete(CarrinhoDeCompras carrinho) {
		dataAccessManager.entity(CarrinhoDeCompras.class).delete(carrinho);
		dataAccessManager.flush();
	}
}
