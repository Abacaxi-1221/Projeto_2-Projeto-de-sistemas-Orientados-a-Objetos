package com.luminaria.pedido;

import com.luminaria.model.Lampada;
import com.luminaria.model.VariacaoProduto;
import java.math.BigDecimal;

public class ItemPedido {

    private final Lampada lampada;
    private final VariacaoProduto variacao;
    private final int quantidade;

    public ItemPedido(Lampada lampada, VariacaoProduto variacao, int quantidade) {
        this.lampada = lampada;
        this.variacao = variacao;
        this.quantidade = quantidade;
    }

    public BigDecimal getSubtotal() {
        return variacao.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

    public Lampada getLampada() { return lampada; }
    public VariacaoProduto getVariacao() { return variacao; }
    public int getQuantidade() { return quantidade; }

    @Override
    public String toString() {
        return quantidade + "x " + lampada.getNome() + " (" + variacao.getVoltagem() + "V) = R$ " + getSubtotal();
    }
}
