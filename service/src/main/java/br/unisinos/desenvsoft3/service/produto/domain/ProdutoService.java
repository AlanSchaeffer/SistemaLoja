package br.unisinos.desenvsoft3.service.produto.domain;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.produto.dao.ProdutoDAO;
import br.unisinos.desenvsoft3.model.produto.domain.Produto;
import br.unisinos.desenvsoft3.model.produto.repository.ListagemProdutosRepository;
import br.unisinos.desenvsoft3.model.produto.repository.ProdutoFilter;
import br.unisinos.desenvsoft3.model.produto.repository.ProdutoListado;
import br.unisinos.desenvsoft3.model.produto.repository.ProdutoView;
import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;
import br.unisinos.desenvsoft3.service.generic.util.ValidationContext;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private ListagemProdutosRepository listagemProdutosRepository;
	
	@Autowired
	private ValorProdutoService valorProdutoService;
	
	public GenericResponse salvar(CadastroProdutoRequest request) {
		return ValidationContext.forBean(request)
								.ifValid(r -> r.getNome() != null, "Nome obrigat�rio")
								.ifValid(r -> StringUtils.isNotBlank(r.getDescricao()), "Descri��o obrigat�ria")
								.ifValid(r -> r.getEstoque() != null, "Estoque obrigat�rio")
								.ifValid(r -> r.getEstoque() >= 0, "Estoque n�o pode ser negativo")
								.ifValid(r -> r.getPreco() != null, "Pre�o obrigat�rio")
								.ifValid(r -> r.getPreco() >= 0.0, "Pre�o n�o pode ser negativo")
								.thenDo(r -> {
									Produto produto = loadOrCreate(r);
									produto.setNome(r.getNome());
									produto.setDescricao(r.getDescricao());
									produto.setEstoque(r.getEstoque());
									produto.setPreco(r.getPreco());
									produtoDAO.salvar(produto);									
								});
	}
	
	private Produto loadOrCreate(CadastroProdutoRequest request) {
		if(request.getId() == null) {
			return new Produto();
		} else {
			return produtoDAO.carregar(request.getId());
		}
	}
	
	public List<ProdutoListado> listarProdutos(ProdutoFilter filtro) {
		List<ProdutoListado> produtos = listagemProdutosRepository.pesquisar(filtro);
		
		produtos.forEach(produto -> {
			Double valorProdutoComPromocao = valorProdutoService.getValorProduto(produto.getId());
			produto.setPreco(valorProdutoComPromocao);
		});
		
		return produtos;
	}
	
	public ProdutoView visualizar(Integer id) {
		ProdutoView produto = listagemProdutosRepository.visualizar(id);
		
		if(produto != null) {
			Double valorProdutoComPromocao = valorProdutoService.getValorProduto(produto.getId());
			produto.setPreco(valorProdutoComPromocao);
		}
		
		return produto;
	}
}
