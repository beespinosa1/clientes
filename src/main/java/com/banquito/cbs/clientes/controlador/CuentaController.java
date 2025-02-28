package com.banquito.cbs.clientes.controlador;

import com.banquito.cbs.clientes.controlador.mapper.CuentaMapper;
import com.banquito.cbs.clientes.dto.CuentaDto;
import com.banquito.cbs.clientes.modelo.Cuenta;
import com.banquito.cbs.clientes.servicio.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/cuentas")
@CrossOrigin("*")
@Tag(name = "Cuenta", description = "API para la gestión de cuentas bancarias")
public class CuentaController {

    private final CuentaService cuentaService;
    private final CuentaMapper cuentaMapper;
    
    public CuentaController(CuentaService cuentaService, CuentaMapper cuentaMapper) {
        this.cuentaService = cuentaService;
        this.cuentaMapper = cuentaMapper;
    }

    @Operation(summary = "Crear una nueva cuenta", description = "Registra una nueva cuenta bancaria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cuenta creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de la cuenta inválidos")
    })
    @PostMapping
    public ResponseEntity<CuentaDto> crearCuenta(
            @Parameter(description = "Datos de la cuenta a crear")
            @Valid @RequestBody CuentaDto cuentaDto) {
        Cuenta cuenta = cuentaMapper.toModel(cuentaDto);
        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuenta);
        return new ResponseEntity<>(cuentaDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener cuenta por ID", description = "Retorna una cuenta específica por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuenta(
            @Parameter(description = "ID de la cuenta a buscar", required = true)
            @PathVariable Integer id) {
        Cuenta cuenta = cuentaService.buscarPorId(id);
        return ResponseEntity.ok(cuenta);
    }

    @Operation(summary = "Obtener cuenta por número", description = "Retorna una cuenta específica por su número de cuenta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @GetMapping("/numero/{numero}")
    public ResponseEntity<Cuenta> obtenerCuentaPorNumero(
            @Parameter(description = "Número de cuenta a buscar", required = true)
            @PathVariable String numero) {
        Cuenta cuenta = cuentaService.buscarPorNumero(numero);
        return ResponseEntity.ok(cuenta);
    }

    @Operation(summary = "Listar cuentas por cliente", description = "Obtiene todas las cuentas asociadas a un cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuentas listadas exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping
    public ResponseEntity<List<Cuenta>> listarCuentasPorCliente(
            @Parameter(description = "ID del cliente", required = true)
            @RequestParam("clienteId") Integer clienteId) {
        List<Cuenta> cuentas = cuentaService.listarPorCliente(clienteId);
        return ResponseEntity.ok(cuentas);
    }

    @Operation(summary = "Realizar depósito", description = "Deposita un valor en una cuenta específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Depósito realizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "400", description = "Valor de depósito inválido")
    })
    @PutMapping("/{id}/deposito")
    public ResponseEntity<Void> depositarEnCuenta(
            @Parameter(description = "ID de la cuenta", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Valor a depositar", required = true)
            @RequestParam BigDecimal valor) {
        Cuenta cuenta = cuentaService.buscarPorId(id);
        cuentaService.depositarValores(cuenta, valor);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Pre-acreditar valores", description = "Pre-acredita un valor en una cuenta específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pre-acreditación realizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "400", description = "Valor de pre-acreditación inválido")
    })
    @PutMapping("/{id}/pre-acreditar")
    public ResponseEntity<Void> preAcreditarValores(
            @Parameter(description = "ID de la cuenta", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Valor a pre-acreditar", required = true)
            @RequestParam BigDecimal valor) {
        Cuenta cuenta = cuentaService.buscarPorId(id);
        cuentaService.preAcreditarValores(cuenta, valor);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Acreditar valores", description = "Acredita los valores pre-acreditados en una cuenta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Acreditación realizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @PutMapping("/{id}/acreditar")
    public ResponseEntity<Void> acreditarValores(
            @Parameter(description = "ID de la cuenta", required = true)
            @PathVariable Integer id) {
        Cuenta cuenta = cuentaService.buscarPorId(id);
        cuentaService.acreditarValores(cuenta);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Activar cuenta", description = "Activa una cuenta específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta activada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @PutMapping("/{id}/activar")
    public ResponseEntity<Void> activarCuenta(
            @Parameter(description = "ID de la cuenta a activar", required = true)
            @PathVariable Integer id) {
        cuentaService.activarCuenta(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Inactivar cuenta", description = "Inactiva una cuenta específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta inactivada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @PutMapping("/{id}/inactivar")
    public ResponseEntity<Void> inactivarCuenta(
            @Parameter(description = "ID de la cuenta a inactivar", required = true)
            @PathVariable Integer id) {
        cuentaService.inactivarCuenta(id);
        return ResponseEntity.ok().build();
    }
}
