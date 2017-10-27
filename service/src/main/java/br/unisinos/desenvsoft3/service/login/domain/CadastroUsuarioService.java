package br.unisinos.desenvsoft3.service.login.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.login.dao.UsuarioDAO;
import br.unisinos.desenvsoft3.model.login.domain.Usuario;
import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;
import br.unisinos.desenvsoft3.service.generic.util.ValidationContext;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public GenericResponse cadastrar(CadastroUsuarioRequest request) {
		return ValidationContext.forBean(request)
								.ifValid(r -> !isUsuarioJaCadastrado(r), "Nome de usuário ou e-mail já cadastrado")
								.ifValid(r -> validaEmail(r), "E-mail inválido")
								.thenDo(r -> {
									Usuario usuario = new Usuario();
									usuario.setNome(r.getNome());
									usuario.setSenha(new SenhaCriptografada(r.getSenha()).toString());
									usuario.setEmail(r.getEmail());
									usuarioDAO.salvar(usuario);
								});
	}
	
	private boolean validaEmail(CadastroUsuarioRequest request) {
		return request.getEmail().matches("[a-zA-Z0-9_]+@[a-zA-Z0-9_]+\\.[a-zA-Z0-9_.]+");
	}

	private boolean isUsuarioJaCadastrado(CadastroUsuarioRequest request) {
		return usuarioDAO.existeComNomeOuEmail(request.getNome(), request.getEmail());
	}
}
