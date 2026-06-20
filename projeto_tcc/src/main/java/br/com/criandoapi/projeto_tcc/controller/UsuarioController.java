package br.com.criandoapi.projeto_tcc.controller;

import br.com.criandoapi.projeto_tcc.model.AtualizarUsuario;
import br.com.criandoapi.projeto_tcc.model.Credenciais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.criandoapi.projeto_tcc.model.Usuario;
import br.com.criandoapi.projeto_tcc.service.UsuarioService;

@RestController
@CrossOrigin("*") // libera todas as entradas que vierem da máquina
@RequestMapping("/usuarios") // ao colocar o endpoint aqui, esse endereço será usado para as rotas seguintes
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	// @GetMapping
	// public List<Usuario> listaUsuarios() {
	// return service.listarUsuarios();

	// @GetMapping("/usuarios") Endpoint que lista os usuários existentes no banco.
	// Ele foi retirado daqui e colocado em @RequestMapping para ser reaproveitado
	// em outras rotas.
	// public List<Usuario> listaUsuarios () {
	// return (List<Usuario>) dao.findAll();
	// }

	@PostMapping
	public Usuario criarUsuario(@RequestBody Usuario usuario) {
		return service.criarUsuario(usuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> editarUsuario(@PathVariable Integer id, @RequestBody AtualizarUsuario dados) {
		Usuario usuarioLogado = service.login(dados.getEmail(), dados.getSenha());

		if (usuarioLogado == null) {
			return ResponseEntity.status(401).build();
		}

		if (!usuarioLogado.getId().equals(id)) {
			return ResponseEntity.status(403).build();
		}

		Usuario usuario = dados.getUsuario();
		usuario.setId(id);
		return ResponseEntity.ok(service.editarUsuario(usuario));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirUsuario(@PathVariable Integer id, @RequestBody Credenciais credenciais) {

		Usuario usuarioLogado = service.login(credenciais.getEmail(), credenciais.getSenha());

		if (usuarioLogado == null) {
			return ResponseEntity.status(401).build();
		}

		if (!usuarioLogado.getId().equals(id)) {
			return ResponseEntity.status(403).build();
		}
		service.excluirUsuario(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/login")
	public ResponseEntity<Usuario> login(
			@RequestParam String email,
			@RequestParam String senha) {

		Usuario usuario = service.login(email, senha);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuario);

	}

	@PostMapping("/{id}/consultar")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id, @RequestBody Credenciais credenciais) {
		Usuario usuario = service.login(credenciais.getEmail(), credenciais.getSenha());

		if (usuario == null) {
			return ResponseEntity.status(401).build();
		}

		if (!usuario.getId().equals(id)) {
			return ResponseEntity.status(403).build();
		}

		return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
}
