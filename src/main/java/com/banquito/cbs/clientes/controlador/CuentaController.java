package com.banquito.cbs.clientes.controlador;

import com.banquito.cbs.clientes.modelo.Cuenta;
import com.banquito.cbs.clientes.servicio.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/cuentas")
@CrossOrigin("*")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(
            @Valid @RequestBody Cuenta cuenta,
            @RequestParam BigDecimal depositoInicial) {
        cuentaService.crearCuenta(cuenta, depositoInicial);
        return new ResponseEntity<>(cuenta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable Integer id) {
        Cuenta cuenta = cuentaService.buscarPorId(id);
        return ResponseEntity.ok(cuenta);
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<Cuenta> obtenerCuentaPorNumero(@PathVariable String numero) {
        Cuenta cuenta = cuentaService.buscarPorNumero(numero);
        return ResponseEntity.ok(cuenta);
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> listarCuentasPorCliente(
            @RequestParam("clienteId") Integer clienteId) {
        List<Cuenta> cuentas = cuentaService.listarPorCliente(clienteId);
        return ResponseEntity.ok(cuentas);
    }

    @PutMapping("/{id}/deposito")
    public ResponseEntity<Void> depositarEnCuenta(
            @PathVariable Integer id,
            @RequestParam BigDecimal valor) {
        Cuenta cuenta = cuentaService.buscarPorId(id);
        cuentaService.depositarValores(cuenta, valor);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/pre-acreditar")
    public ResponseEntity<Void> preAcreditarValores(
            @PathVariable Integer id,
            @RequestParam BigDecimal valor) {
        Cuenta cuenta = cuentaService.buscarPorId(id);
        cuentaService.preAcreditarValores(cuenta, valor);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/acreditar")
    public ResponseEntity<Void> acreditarValores(@PathVariable Integer id) {
        Cuenta cuenta = cuentaService.buscarPorId(id);
        cuentaService.acreditarValores(cuenta);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<Void> activarCuenta(@PathVariable Integer id) {
        cuentaService.activarCuenta(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/inactivar")
    public ResponseEntity<Void> inactivarCuenta(@PathVariable Integer id) {
        cuentaService.inactivarCuenta(id);
        return ResponseEntity.ok().build();
    }
}
