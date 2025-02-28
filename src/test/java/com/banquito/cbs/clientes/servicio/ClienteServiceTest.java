package com.banquito.cbs.clientes.servicio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.banquito.cbs.clientes.modelo.Cliente;
import com.banquito.cbs.clientes.repositorio.ClienteRepository;
import com.banquito.cbs.clientes.excepcion.EntidadNoEncontradaException;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setTipoCliente("NAT"); 
        cliente.setTipoDocumento("CED");
        cliente.setNumeroDocumento("1234567890");
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setNombreCompleto("Juan Pérez");
        cliente.setEmail("juan.perez@mail.com");
        cliente.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        cliente.setNombreComercio(null); 
        cliente.setTipoComercio(null);
        cliente.setFechaConstitucion(null);
        cliente.setEstado("ACT");
        cliente.setNacionalidad("ECU");
        cliente.setEstadoCivil("SOL");
        cliente.setIngresoPromedioMensual(new BigDecimal("1000.00"));
    }

    @Test
    void whenCrearCliente_thenReturnClienteCreado() {
        // Arrange
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        // Act
        Cliente clienteCreado = clienteService.crear(cliente);

        // Assert
        assertNotNull(clienteCreado);
        assertEquals("1234567890", clienteCreado.getNumeroDocumento());
        assertEquals("Juan Pérez", clienteCreado.getNombreCompleto());
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void whenBuscarClientePorId_thenReturnCliente() {
        // Arrange
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        // Act
        Cliente clienteEncontrado = clienteService.obtenerPorId(1);

        // Assert
        assertNotNull(clienteEncontrado);
        assertEquals(1, clienteEncontrado.getId());
        assertEquals("1234567890", clienteEncontrado.getNumeroDocumento());
        assertEquals("NAT", clienteEncontrado.getTipoCliente());
    }

    @Test
    void whenBuscarClienteInexistente_thenThrowException() {
        // Arrange
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntidadNoEncontradaException.class, () -> {
            clienteService.obtenerPorId(99);
        });
    }

    @Test
    void whenListarClientes_thenReturnListaClientes() {
        // Arrange
        List<Cliente> clientes = Arrays.asList(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);

        // Act
        List<Cliente> listaClientes = clienteService.listarTodos();

        // Assert
        assertNotNull(listaClientes);
        assertEquals(1, listaClientes.size());
        assertEquals("Juan Pérez", listaClientes.get(0).getNombreCompleto());
        verify(clienteRepository).findAll();
    }

    @Test
    void whenCrearClienteJuridico_thenReturnClienteCreado() {
        // Arrange
        Cliente clienteJuridico = new Cliente();
        clienteJuridico.setId(2);
        clienteJuridico.setTipoCliente("JUR"); 
        clienteJuridico.setTipoDocumento("RUC");
        clienteJuridico.setNumeroDocumento("1234567890001");
        clienteJuridico.setNombreCompleto("Empresa XYZ S.A.");
        clienteJuridico.setEmail("contacto@xyz.com");
        clienteJuridico.setNombreComercio("XYZ Comercial");
        clienteJuridico.setTipoComercio("COMERCIAL");
        clienteJuridico.setFechaConstitucion(LocalDate.of(2000, 1, 1));
        clienteJuridico.setEstado("ACT");
        clienteJuridico.setNacionalidad("ECU");
        clienteJuridico.setIngresoPromedioMensual(new BigDecimal("10000.00"));

        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteJuridico);

        // Act
        Cliente clienteCreado = clienteService.crear(clienteJuridico);

        // Assert
        assertNotNull(clienteCreado);
        assertEquals("1234567890001", clienteCreado.getNumeroDocumento());
        assertEquals("JUR", clienteCreado.getTipoCliente());
        assertEquals("XYZ Comercial", clienteCreado.getNombreComercio());
        verify(clienteRepository).save(any(Cliente.class));
    }
} 