package br.com.criandoapi.projeto_tcc.DAO;

import org.springframework.data.repository.CrudRepository;

import br.com.criandoapi.projeto_tcc.model.Usuario;

public interface IUsuario extends CrudRepository<Usuario, Integer>{

}
