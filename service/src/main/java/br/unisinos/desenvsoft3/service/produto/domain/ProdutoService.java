package br.unisinos.desenvsoft3.service.produto.domain;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.produto.dao.ListagemProdutosDAO;
import br.unisinos.desenvsoft3.model.produto.dao.ProdutoDAO;
import br.unisinos.desenvsoft3.model.produto.domain.Produto;
import br.unisinos.desenvsoft3.model.produto.domain.ProdutoFilter;
import br.unisinos.desenvsoft3.model.produto.domain.ProdutoListado;
import br.unisinos.desenvsoft3.model.produto.domain.ProdutoView;
import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;
import br.unisinos.desenvsoft3.service.generic.util.ValidationContext;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private ListagemProdutosDAO listagemProdutosDAO;
	
	public GenericResponse salvar(CadastroProdutoRequest request) {
		return ValidationContext.forBean(request)
								.ifValid(r -> r.getNome() != null, "Nome obrigatório")
								.ifValid(r -> StringUtils.isNotBlank(r.getDescricao()), "Descrição obrigatória")
								.ifValid(r -> r.getEstoque() != null, "Estoque obrigatório")
								.ifValid(r -> r.getEstoque() >= 0, "Estoque não pode ser negativo")
								.ifValid(r -> r.getPreco() != null, "Preço obrigatório")
								.ifValid(r -> r.getPreco() >= 0.0, "Preço não pode ser negativo")
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
		return listagemProdutosDAO.pesquisar(filtro);
	}
	
	public ProdutoView visualizar(Integer id) {
		return listagemProdutosDAO.visualizar(id);
	}
}
