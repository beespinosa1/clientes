package com.banquito.cbs.clientes.controlador;

import com.banquito.cbs.clientes.modelo.Cliente;
import com.banquito.cbs.clientes.dto.ClienteDto;
import com.banquito.cbs.clientes.servicio.ClienteService;
import com.banquito.cbs.clientes.controlador.mapper.ClienteMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/clientes")
@CrossOrigin("*")
@Tag(name = "Cliente", description = "API para la gestión de clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Registra un nuevo cliente en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos del cliente inválidos")
    })
    @PostMapping
    public ResponseEntity<ClienteDto> crearCliente(
            @Parameter(description = "Datos del cliente a crear") 
            @Valid @RequestBody ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.toModel(clienteDto);
        Cliente nuevoCliente = clienteService.crear(cliente);
        return new ResponseEntity<>(clienteMapper.toDto(nuevoCliente), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener cliente por ID", description = "Retorna un cliente específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerCliente(
            @Parameter(description = "ID del cliente a buscar", required = true)
            @PathVariable Integer id) {
        Cliente cliente = clienteService.obtenerPorId(id);
        return ResponseEntity.ok(clienteMapper.toDto(cliente));
    }

    @Operation(summary = "Listar todos los clientes", description = "Obtiene una lista de todos los clientes registrados")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        List<Cliente> clientes = clienteService.listarTodos();
        List<ClienteDto> clientesDto = clientes.stream()
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientesDto);
    }

    @Operation(summary = "Actualizar cliente", description = "Actualiza los datos de un cliente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizarCliente(
            @Parameter(description = "ID del cliente a actualizar", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Nuevos datos del cliente")
            @Valid @RequestBody ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.toModel(clienteDto);
        Cliente clienteActualizado = clienteService.actualizar(id, cliente);
        return ResponseEntity.ok(clienteMapper.toDto(clienteActualizado));
    }

   


}
