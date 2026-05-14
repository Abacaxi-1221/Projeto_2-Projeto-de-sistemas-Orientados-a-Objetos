package com.luminaria.model.enums;

public enum StatusPedido {
    RASCUNHO, PAGO, ENVIADO, CANCELADO;

    public StatusPedido proximo() {
        switch (this) {
            case RASCUNHO: return PAGO;
            case PAGO: return ENVIADO;
            default: throw new IllegalStateException("Pedido em estado " + this + " não pode avançar.");
        }
    }
}
