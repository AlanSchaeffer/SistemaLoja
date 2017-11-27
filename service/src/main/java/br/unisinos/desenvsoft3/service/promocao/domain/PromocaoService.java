package br.unisinos.desenvsoft3.service.promocao.domain;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.produto.dao.ProdutoDAO;
import br.unisinos.desenvsoft3.model.promocao.dao.PromocaoDAO;
import br.unisinos.desenvsoft3.model.promocao.domain.Promocao;
import br.unisinos.desenvsoft3.model.promocao.repository.ListagemPromocoesRepository;
import br.unisinos.desenvsoft3.model.promocao.repository.PromocaoView;
import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;
import br.unisinos.desenvsoft3.service.generic.util.ValidationContext;

@Service
public class PromocaoService {

	@Autowired
	private PromocaoDAO promocaoDAO;
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private ListagemPromocoesRepository listagemPromocoesRepository;
	
	public GenericResponse criarPromocao(CriarPromocaoRequest request) {
		return ValidationContext.forBean(request)
								.ifValid(r -> !r.getIdsProdutos().isEmpty(), "Informe os produtos.")
								.ifValid(r -> r.getDtInicio() != null, "Informe a data inicial.")
								.ifValid(r -> r.getDtFim() != null, "Informe a data final.")
								.ifValid(r -> r.getPeDesconto() != null, "Informe o % de desconto.")
								.ifValid(r -> r.getPeDesconto() > 0D && r.getPeDesconto() < 100D, "Desconto deve estar entre 0 e 100%.")
								.ifValid(r -> produtoDAO.existemTodosProdutos(r.getIdsProdutos()), "Produtos inválidos.")
								.ifValid(r -> r.getDtFim().toInstant().atOffset(ZoneOffset.UTC).toLocalDate().isAfter(LocalDate.now()), "Data final não pode ser menor que data atual")
								.thenDo(this::salvarPromocoes);
	}
	
	private void salvarPromocoes(CriarPromocaoRequest request) {
		List<Promocao> promocoes = request.getIdsProdutos()
										  .stream()
										  .map(idProduto -> {
											  Promocao promocao = new Promocao();
											  promocao.setProduto(produtoDAO.carregar(idProduto));
											  promocao.setDtInicial(request.getDtInicio());
											  promocao.setDtFinal(request.getDtFim());
											  promocao.setPeDesconto(request.getPeDesconto());
											  return promocao;
										  })
										  .collect(Collectors.toList());
		promocaoDAO.salvar(promocoes);
	}
	
	public List<PromocaoView> listarPromocoes() {
		List<PromocaoView> promocoes = listagemPromocoesRepository.listar();
		promocoes = excluiPromocoesEncerradas(promocoes);
		return promocoes;
	}

	private List<PromocaoView> excluiPromocoesEncerradas(List<PromocaoView> promocoes) {
		List<PromocaoView> promocoesEncerradas = promocoes.stream()
														  .filter(PromocaoView::isEncerrada)
														  .collect(Collectors.toList());
		promocaoDAO.deletarPromocoes(promocoesEncerradas.stream().map(PromocaoView::getId).collect(Collectors.toList()));
		promocoes.removeAll(promocoesEncerradas);
		return promocoes;
	}

	public GenericResponse removerPromocao(Integer idPromocao) {
		Promocao promocao = promocaoDAO.get(idPromocao);
		if(promocao == null) {
			return GenericResponse.error("Promoção inexistente.");
		}
		
		promocaoDAO.deletar(promocao);
		return GenericResponse.ok();
	}
}
