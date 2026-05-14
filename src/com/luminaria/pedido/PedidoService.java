package com.luminaria.pedido;

import com.luminaria.model.Lampada;
import com.luminaria.model.VariacaoProduto;
import java.util.UUID;

public class PedidoService {

    public Pedido criarPedido(String cliente) {
        return new Pedido(UUID.randomUUID().toString().substring(0, 8).toUpperCase(), cliente);
    }

    public void adicionarItem(Pedido pedido, Lampada lampada, VariacaoProduto variacao, int quantidade) {
        if (!variacao.temEstoque(quantidade)) {
            throw new IllegalStateException("Estoque insuficiente para " + lampada.getNome());
        }
        pedido.adicionarItem(new ItemPedido(lampada, variacao, quantidade));
    }

    public void confirmarPagamento(Pedido pedido) {
        pedido.getItens().forEach(item -> item.getVariacao().reduzirEstoque(item.getQuantidade()));
        pedido.avancarStatus(); // RASCUNHO → PAGO
        System.out.println("Pagamento confirmado. Pedido #" + pedido.getId() + " agora está: " + pedido.getStatus());
    }

    public void registrarEnvio(Pedido pedido) {
        pedido.avancarStatus(); // PAGO → ENVIADO
        System.out.println("Envio registrado. Pedido #" + pedido.getId() + " agora está: " + pedido.getStatus());
    }
}
