package br.unisinos.desenvsoft3.service.produto.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.produto.dao.ProdutoDAO;

@Service
public class ValorProdutoService {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	public Double getValorProduto(Integer idProduto) {
		return produtoDAO.carregar(idProduto).getPreco();
	}
}
