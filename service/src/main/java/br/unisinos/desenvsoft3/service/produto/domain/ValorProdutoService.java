package br.unisinos.desenvsoft3.service.produto.domain;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.produto.dao.ProdutoDAO;
import br.unisinos.desenvsoft3.model.produto.domain.Produto;
import br.unisinos.desenvsoft3.model.promocao.dao.PromocaoDAO;
import br.unisinos.desenvsoft3.model.promocao.domain.Promocao;

@Service
public class ValorProdutoService {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private PromocaoDAO promocaoDAO;
	
	public Double getValorProduto(Integer idProduto) {
		Produto produto = produtoDAO.carregar(idProduto);
		double preco = produto.getPreco();
		preco = aplicaPromocao(preco, idProduto);
		return preco;
	}
	
	private double aplicaPromocao(double preco, Integer idProduto) {
		List<Promocao> promocoes = promocaoDAO.getPromocoesDoProdutoNaData(idProduto, Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		if(!promocoes.isEmpty()) {
			Promocao primeiraPromocao = promocoes.get(0);
			preco = preco * primeiraPromocao.getPeDesconto() / 100D;
		}
		
		return preco;
	}
}
