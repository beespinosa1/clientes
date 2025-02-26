package com.banquito.cbs.clientes.repositorio;

import com.banquito.cbs.clientes.modelo.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    List<Cuenta> findByClienteId(Integer clienteId);
    Optional<Cuenta> findByNumero(String numero);
    Optional<Cuenta> findTopByOrderByFechaCreacionDesc();
}
