package com.banquito.cbs.clientes.servicio;

import com.banquito.cbs.clientes.modelo.Cliente;
import com.banquito.cbs.clientes.repositorio.ClienteRepository;
import com.banquito.cbs.clientes.excepcion.EntidadNoEncontradaException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    public Cliente crear(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public Cliente obtenerPorId(Integer id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new EntidadNoEncontradaException("Cliente no encontrado con id: " + id));
    }
    
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
    
    public Cliente actualizar(Integer id, Cliente cliente) {
        obtenerPorId(id); 
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }
    
    public void eliminar(Integer id) {
        Cliente cliente = obtenerPorId(id);
        clienteRepository.delete(cliente);
    }
}
