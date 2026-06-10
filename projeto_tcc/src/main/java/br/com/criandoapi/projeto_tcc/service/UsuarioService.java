package br.com.criandoapi.projeto_tcc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.criandoapi.projeto_tcc.model.Usuario;
import br.com.criandoapi.projeto_tcc.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository dao;

    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) dao.findAll();
    }

    public Usuario criarUsuario(Usuario usuario) {
        return dao.save(usuario);
    }

    public Usuario editarUsuario(Usuario usuario) {
        return dao.save(usuario);
    }

    public Optional<Usuario> excluirUsuario(Integer id) {
        Optional<Usuario> usuario = dao.findById(id);

        if (usuario.isPresent()) {
            dao.deleteById(id);
        }

        return usuario;
    }
}
