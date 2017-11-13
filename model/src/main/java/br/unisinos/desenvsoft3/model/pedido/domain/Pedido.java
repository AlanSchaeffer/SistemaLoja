package br.unisinos.desenvsoft3.model.pedido.domain;

import java.util.ArrayList;
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
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import br.unisinos.desenvsoft3.model.generic.domain.EnumDomainType;
import br.unisinos.desenvsoft3.model.login.domain.Usuario;

@Entity
@Table(name = "PEDIDOS")
@TypeDef(name = "status_pedido", typeClass = EnumDomainType.class, parameters = {
		@Parameter(name = "enumClassName", value = "br.unisinos.desenvsoft3.model.pedido.domain.StatusPedido")
})
public class Pedido {

	@Id
	@GenericGenerator(name = "SEQ_PEDIDOS", strategy = "sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PEDIDOS")
	@Column(name = "ID")
	private Integer id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@NotNull
	@Column(name = "VL_FRETE")
	private Double vlFrete;
	
	@NotNull
	@Column(name = "STATUS")
	@Type(type = "status_pedido")
	private StatusPedido statusPedido = StatusPedido.CRIADO;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "pedido")
	private List<PedidoItem> itens = new ArrayList<>();
	
	@Version
	@Column(name = "VERSION")
	private Integer version;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getVlFrete() {
		return vlFrete;
	}

	public void setVlFrete(Double vlFrete) {
		this.vlFrete = vlFrete;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public Integer getId() {
		return id;
	}

	public List<PedidoItem> getItens() {
		return itens;
	}
}
