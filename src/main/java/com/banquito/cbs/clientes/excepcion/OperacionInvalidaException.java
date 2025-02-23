package com.banquito.cbs.clientes.excepcion;

public class OperacionInvalidaException extends RuntimeException {
    public OperacionInvalidaException(String message) {
        super(message);
    }
}
