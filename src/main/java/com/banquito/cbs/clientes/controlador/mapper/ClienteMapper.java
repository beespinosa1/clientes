package com.banquito.cbs.clientes.controlador.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.banquito.cbs.clientes.dto.ClienteDto;
import com.banquito.cbs.clientes.modelo.Cliente;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {
    
    ClienteDto toDto(Cliente model);

    Cliente toModel(ClienteDto clienteDto);
}
