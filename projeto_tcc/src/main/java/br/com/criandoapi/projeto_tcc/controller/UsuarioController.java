package br.com.criandoapi.projeto_tcc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.criandoapi.projeto_tcc.model.Usuario;
import br.com.criandoapi.projeto_tcc.service.UsuarioService;

@RestController
@CrossOrigin("*")//libera todas as entradas que vierem da máquina
@RequestMapping("/usuarios")//ao colocar o endpoint aqui, esse endereço será usado para as rotas seguintes
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public List<Usuario> listaUsuarios () {
		return service.listarUsuarios();
		
	//@GetMapping("/usuarios") Endpoint que lista os usuários existentes no banco. Ele foi retirado daqui e colocado em @RequestMapping para ser reaproveitado em outras rotas.
	//public List<Usuario> listaUsuarios () {
		//return (List<Usuario>) dao.findAll();
	}	
	
	@PostMapping	
	public Usuario criarUsuario (@RequestBody Usuario usuario) {
		return service.criarUsuario(usuario);
	}
	
	@PutMapping
	public Usuario editarUsuario (@RequestBody Usuario usuario) {
		return service.editarUsuario(usuario);
	}	
	
	@DeleteMapping("/{id}")
	public Optional<Usuario> excluirUsuario (@PathVariable Integer id){
		return service.excluirUsuario(id);
	}
}


