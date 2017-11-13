package br.unisinos.desenvsoft3.model.pedido.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.unisinos.desenvsoft3.model.produto.domain.Produto;

@Entity
@Table(name = "CARRINHOS_DE_COMPRAS_ITENS")
public class CarrinhoDeComprasItem {

	@Id
	@GenericGenerator(name = "SEQ_CARRINHOS_ITENS", strategy = "sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARRINHOS_ITENS")
	@Column(name = "ID")
	private Integer id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_CARRINHO")
	private CarrinhoDeCompras carrinhoDeCompras;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_PRODUTO")
	private Produto produto;
	
	@NotNull
	@Column(name = "QUANTIDADE")
	private Integer quantidade;
	
	@Version
	@Column(name = "VERSION")
	private Integer version;

	public CarrinhoDeCompras getCarrinhoDeCompras() {
		return carrinhoDeCompras;
	}

	public void setCarrinhoDeCompras(CarrinhoDeCompras carrinhoDeCompras) {
		this.carrinhoDeCompras = carrinhoDeCompras;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getId() {
		return id;
	}
}
