package com.luminaria.model;

import com.luminaria.model.enums.TipoSoquete;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Classe base abstrata para todos os tipos de lâmpada. */
public abstract class Lampada {

    private final String id;
    private final String nome;
    private final String marca;
    private final int lumens;
    private final TipoSoquete soquete;

    // Composição: uma Lampada possui múltiplas variações de produto
    private final List<VariacaoProduto> variacoes = new ArrayList<>();

    public Lampada(String id, String nome, String marca, int lumens, TipoSoquete soquete) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.lumens = lumens;
        this.soquete = soquete;
    }

    public abstract String getTipo();

    /** Retorna lumens por watt (eficiência luminosa). */
    public abstract double calcularEficiencia();

    public void adicionarVariacao(VariacaoProduto v) {
        variacoes.add(v);
    }

    public Optional<VariacaoProduto> getVariacaoPorVoltagem(int voltagem) {
        return variacoes.stream().filter(v -> v.getVoltagem() == voltagem).findFirst();
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getMarca() { return marca; }
    public int getLumens() { return lumens; }
    public TipoSoquete getSoquete() { return soquete; }
    public List<VariacaoProduto> getVariacoes() { return variacoes; }

    @Override
    public String toString() {
        return "[" + getTipo() + "] " + nome + " | " + marca + " | " + lumens + " lm | Soquete: " + soquete;
    }
}
