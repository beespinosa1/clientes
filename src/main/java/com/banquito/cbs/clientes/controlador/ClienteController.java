package com.banquito.cbs.clientes.controlador;

import com.banquito.cbs.clientes.modelo.Cliente;
import com.banquito.cbs.clientes.dto.ClienteDto;
import com.banquito.cbs.clientes.servicio.ClienteService;
import com.banquito.cbs.clientes.controlador.mapper.ClienteMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping
    public ResponseEntity<ClienteDto> crearCliente(@Valid @RequestBody ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.toModel(clienteDto);
        Cliente nuevoCliente = clienteService.crear(cliente);
        return new ResponseEntity<>(clienteMapper.toDto(nuevoCliente), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerCliente(@PathVariable Integer id) {
        Cliente cliente = clienteService.obtenerPorId(id);
        return ResponseEntity.ok(clienteMapper.toDto(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        List<Cliente> clientes = clienteService.listarTodos();
        List<ClienteDto> clientesDto = clientes.stream()
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientesDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizarCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.toModel(clienteDto);
        Cliente clienteActualizado = clienteService.actualizar(id, cliente);
        return ResponseEntity.ok(clienteMapper.toDto(clienteActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
