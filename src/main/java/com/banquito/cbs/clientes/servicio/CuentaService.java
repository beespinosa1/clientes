package com.banquito.cbs.clientes.servicio;

import com.banquito.cbs.clientes.modelo.Cuenta;
import com.banquito.cbs.clientes.repositorio.CuentaRepository;
import com.banquito.cbs.clientes.excepcion.EntidadNoEncontradaException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CuentaService {
    
    private final CuentaRepository cuentaRepository;
    
    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }
    
    public Cuenta crear(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }
    
    public Cuenta obtenerPorId(Integer id) {
        return cuentaRepository.findById(id)
            .orElseThrow(() -> new EntidadNoEncontradaException("Cuenta no encontrada con id: " + id));
    }
    
    public List<Cuenta> listarTodos() {
        return cuentaRepository.findAll();
    }
    
    public Cuenta actualizar(Integer id, Cuenta cuenta) {
        obtenerPorId(id); 
        cuenta.setId(id);
        return cuentaRepository.save(cuenta);
    }
    
    public void eliminar(Integer id) {
        Cuenta cuenta = obtenerPorId(id);
        cuentaRepository.delete(cuenta);
    }
}
