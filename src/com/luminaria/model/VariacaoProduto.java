package com.luminaria.model;

import java.math.BigDecimal;

/** Armazena uma combinação específica de voltagem, cor e estoque de uma lâmpada (composição). */
public class VariacaoProduto {

    private final int voltagem;
    private final String corLuz;
    private final double potenciaWatts;
    private int estoque;
    private final BigDecimal preco;

    public VariacaoProduto(int voltagem, String corLuz, double potenciaWatts, int estoque, BigDecimal preco) {
        this.voltagem = voltagem;
        this.corLuz = corLuz;
        this.potenciaWatts = potenciaWatts;
        this.estoque = estoque;
        this.preco = preco;
    }

    public boolean temEstoque(int quantidade) {
        return estoque >= quantidade;
    }

    public void reduzirEstoque(int quantidade) {
        if (!temEstoque(quantidade)) throw new IllegalStateException("Estoque insuficiente.");
        estoque -= quantidade;
    }

    public int getVoltagem() { return voltagem; }
    public String getCorLuz() { return corLuz; }
    public double getPotenciaWatts() { return potenciaWatts; }
    public int getEstoque() { return estoque; }
    public BigDecimal getPreco() { return preco; }

    @Override
    public String toString() {
        return voltagem + "V | " + corLuz + " | " + potenciaWatts + "W | Estoque: " + estoque + " | R$ " + preco;
    }
}
