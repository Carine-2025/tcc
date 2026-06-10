package br.com.criandoapi.projeto_tcc.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.criandoapi.projeto_tcc.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
	
	Usuario findByEmailAndSenha(String email, String senha);

}
