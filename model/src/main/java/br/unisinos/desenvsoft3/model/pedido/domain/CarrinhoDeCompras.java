package br.unisinos.desenvsoft3.model.pedido.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.unisinos.desenvsoft3.model.login.domain.Usuario;

@Entity
@Table(name = "CARRINHOS_DE_COMPRAS")
public class CarrinhoDeCompras {

	@Id
	@GenericGenerator(name = "SEQ_CARRINHOS", strategy = "sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARRINHOS")
	@Column(name = "ID")
	private Integer id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "carrinhoDeCompras")
	private List<CarrinhoDeComprasItem> itens;

	@Version
	@Column(name = "VERSION")
	private Integer version;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<CarrinhoDeComprasItem> getItens() {
		return itens;
	}

	public Integer getId() {
		return id;
	}
}
