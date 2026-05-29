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

import br.com.criandoapi.projeto_tcc.DAO.IUsuario;
import br.com.criandoapi.projeto_tcc.model.Usuario;

@RestController
@CrossOrigin("*")//libera todas as entradas que vierem da máquina
@RequestMapping("/usuarios")//ao colocar o endpoint aqui, esse endereço será usado para as rotas seguintes
public class UsuarioController {
	
	@Autowired
	private IUsuario dao;
	
	@GetMapping
	public List<Usuario> listaUsuarios () {
		return (List<Usuario>) dao.findAll();
		
	//@GetMapping("/usuarios") Endpoint que lista os usuários existentes no banco. Ele foi retirado daqui e colocado em @RequestMapping para ser reaproveitado em outras rotas.
	//public List<Usuario> listaUsuarios () {
		//return (List<Usuario>) dao.findAll();
	}	
	
	@PostMapping	
	public Usuario criarUsuario (@RequestBody Usuario usuario) {
		Usuario usuarioNovo = dao.save(usuario);
		return usuarioNovo;
	}
	
	@PutMapping
	public Usuario editarUsuario (@RequestBody Usuario usuario) {
		Usuario usuarioNovo = dao.save(usuario);
		return usuarioNovo;
	}	
	
	@DeleteMapping("/{id}")
	public Optional<Usuario> excluirUsuario (@PathVariable Integer id){
		Optional<Usuario> usuario = dao.findById(id);
	  	dao.deleteById(id);
	  	return usuario;
	}
}


