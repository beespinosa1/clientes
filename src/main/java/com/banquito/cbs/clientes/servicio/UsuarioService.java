package com.banquito.cbs.clientes.servicio;

import com.banquito.cbs.clientes.modelo.Usuario;
import com.banquito.cbs.clientes.repositorio.UsuarioRepository;
import com.banquito.cbs.clientes.excepcion.EntidadNoEncontradaException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public Usuario crear(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new EntidadNoEncontradaException("Usuario no encontrado con id: " + id));
    }
    
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    
    public Usuario actualizar(Integer id, Usuario usuario) {
        obtenerPorId(id); 
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }
    
    public void eliminar(Integer id) {
        Usuario usuario = obtenerPorId(id);
        usuarioRepository.delete(usuario);
    }
}
