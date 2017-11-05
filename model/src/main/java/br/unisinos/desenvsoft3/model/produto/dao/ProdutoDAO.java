package br.unisinos.desenvsoft3.model.produto.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.unisinos.desenvsoft3.model.generic.dao.DataAccessManager;
import br.unisinos.desenvsoft3.model.produto.domain.Produto;

@Repository
public class ProdutoDAO {

	@Autowired
	private DataAccessManager dataAccessManager;
	
	@Transactional
	public void salvar(Produto produto) {
		dataAccessManager.entity(Produto.class).save(produto);
		dataAccessManager.flush();
	}
	
	public List<Produto> listar() {
		return dataAccessManager.entity(Produto.class).list();
	}
	
	public Produto carregar(Integer idProduto) {
		return dataAccessManager.entity(Produto.class).load(idProduto);
	}
}
