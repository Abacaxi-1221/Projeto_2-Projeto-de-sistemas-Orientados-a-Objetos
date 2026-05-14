package com.luminaria.pedido;

import com.luminaria.model.enums.StatusPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private final String id;
    private final String cliente;
    private final List<ItemPedido> itens = new ArrayList<>();
    private StatusPedido status = StatusPedido.RASCUNHO;
    private final LocalDateTime criacao = LocalDateTime.now();

    public Pedido(String id, String cliente) {
        this.id = id;
        this.cliente = cliente;
    }

    public void adicionarItem(ItemPedido item) {
        if (status != StatusPedido.RASCUNHO) throw new IllegalStateException("Só é possível adicionar itens em pedidos RASCUNHO.");
        itens.add(item);
    }

    public void avancarStatus() {
        this.status = status.proximo();
    }

    public void cancelar() {
        if (status == StatusPedido.ENVIADO) throw new IllegalStateException("Pedido já enviado não pode ser cancelado.");
        this.status = StatusPedido.CANCELADO;
    }

    public BigDecimal getTotal() {
        return itens.stream().map(ItemPedido::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getId() { return id; }
    public String getCliente() { return cliente; }
    public StatusPedido getStatus() { return status; }
    public List<ItemPedido> getItens() { return itens; }

    @Override
    public String toString() {
        String fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(criacao);
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(id).append(" | Cliente: ").append(cliente)
          .append(" | Status: ").append(status).append(" | ").append(fmt).append("\n");
        itens.forEach(i -> sb.append("  ").append(i).append("\n"));
        sb.append("  TOTAL: R$ ").append(getTotal());
        return sb.toString();
    }
}
