package com.banquito.cbs.clientes.servicio;

import com.banquito.cbs.clientes.modelo.Cuenta;
import com.banquito.cbs.clientes.repositorio.CuentaRepository;
import com.banquito.cbs.clientes.excepcion.EntidadNoEncontradaException;
import com.banquito.cbs.clientes.excepcion.OperacionInvalidaException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;
    public static final String ENTITY_NAME = "Cuenta";

    public static final String TIPO_AHORROS = "AHO";
    public static final String TIPO_CORRIENTE = "COR";

    public static final String ESTADO_ACTIVA = "ACT";
    public static final String ESTADO_INACTIVA = "INA";

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public List<Cuenta> listar() {
        return cuentaRepository.findAll();
    }

    public List<Cuenta> listarPorCliente(Integer clienteId) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        if (cuentas.isEmpty()) {
            throw new EntidadNoEncontradaException("No se encontraron cuentas para el cliente: " + clienteId);
        }
        return cuentas;
    }

    public Cuenta buscarPorId(Integer id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Cuenta no encontrada con id: " + id));
    }

    public Cuenta buscarPorNumero(String numeroCuenta) {
        return this.cuentaRepository.findByNumero(numeroCuenta)
                .orElseThrow(() -> new EntidadNoEncontradaException("Cuenta no encontrada con número: " + numeroCuenta));
    }

    public Cuenta crearCuenta(Cuenta cuenta) {
        if (!cuenta.getTipo().equals(TIPO_AHORROS) && !cuenta.getTipo().equals(TIPO_CORRIENTE)) {
            throw new OperacionInvalidaException("Tipo de cuenta no válido");
        }
        
        cuenta.setNumero(this.generarNuevoNumeroCuenta());
        cuenta.setSaldoAcreditar(BigDecimal.ZERO);
        cuenta.setEstado(ESTADO_ACTIVA);
        cuenta.setFechaCreacion(LocalDateTime.now(ZoneId.systemDefault()));

        this.actualizarTotalCuenta(cuenta);
        return cuentaRepository.save(cuenta);
    }

    public void depositarValores(Cuenta cuenta, BigDecimal valor) {
        cuenta.setSaldoAcreditar(cuenta.getSaldoAcreditar().add(valor));
        this.actualizarTotalCuenta(cuenta);
        cuentaRepository.save(cuenta);
    }

    public void preAcreditarValores(Cuenta cuenta, BigDecimal valor) {
        cuenta.setSaldoAcreditar(cuenta.getSaldoAcreditar().add(valor));
        this.actualizarTotalCuenta(cuenta);
        cuentaRepository.save(cuenta);
    }

    public void acreditarValores(Cuenta cuenta) {
        cuenta.setSaldoTotal(cuenta.getSaldoTotal().add(cuenta.getSaldoAcreditar()));
        cuenta.setSaldoAcreditar(BigDecimal.ZERO);
        this.actualizarTotalCuenta(cuenta);
        cuentaRepository.save(cuenta);
    }

    public void activarCuenta(Integer id) {
        Cuenta cuenta = buscarPorId(id);
        cuenta.setEstado(ESTADO_ACTIVA);
        cuentaRepository.save(cuenta);
    }

    public void inactivarCuenta(Integer id) {
        Cuenta cuenta = buscarPorId(id);
        cuenta.setEstado(ESTADO_INACTIVA);
        cuentaRepository.save(cuenta);
    }

    private String generarNuevoNumeroCuenta() {
        Cuenta cuenta = this.cuentaRepository.findTopByOrderByFechaCreacionDesc().orElse(null);
        BigInteger numero = (cuenta == null) ? 
            BigInteger.ONE : 
            new BigInteger(cuenta.getNumero()).add(BigInteger.ONE);

        BigInteger maxNumero = new BigInteger("9999999999999999");
        if (numero.compareTo(maxNumero) > 0) {
            throw new OperacionInvalidaException("No se pueden generar más números de cuenta");
        }

        return String.format("%08d", numero);
    }

    private void actualizarTotalCuenta(Cuenta cuenta) {
        cuenta.setSaldoTotal(cuenta.getSaldoDisponible().add(cuenta.getSaldoAcreditar()));
    }
}
