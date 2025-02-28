package com.banquito.cbs.clientes.controlador.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.banquito.cbs.clientes.dto.CuentaDto;
import com.banquito.cbs.clientes.modelo.Cuenta;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CuentaMapper {
    
    @Mapping(source = "idCliente", target = "idCliente")
    CuentaDto toDto(Cuenta model);

    @Mapping(source = "idCliente", target = "idCliente")
    Cuenta toModel(CuentaDto cuentaDto);
}
