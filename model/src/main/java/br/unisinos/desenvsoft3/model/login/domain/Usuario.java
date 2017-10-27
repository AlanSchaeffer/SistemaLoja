package br.unisinos.desenvsoft3.model.login.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

	@Id
	@GenericGenerator(name = "SEQ_USUARIOS", strategy = "sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIOS")
	@Column(name = "ID")
	private Integer id;

	@NotBlank
	@Column(name = "NOME")
	private String nome;

	@NotNull
	@Column(name = "SENHA")
	private String senha;

	@NotNull
	@Column(name = "EMAIL")
	private String email;

	@Version
	@Column(name = "VERSION")
	private Integer version;

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
