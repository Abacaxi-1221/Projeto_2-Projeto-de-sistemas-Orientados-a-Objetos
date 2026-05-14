package com.luminaria.model;

import com.luminaria.model.enums.TipoSoquete;

/**
 * Extensão adicionada SEM modificar nenhuma classe existente — demonstra o Princípio Aberto/Fechado (OCP).
 */
public class SmartLampada extends Lampada {

    private final String protocolo;
    private final boolean suportaRgb;

    public SmartLampada(String id, String nome, String marca, int lumens, TipoSoquete soquete,
                        String protocolo, boolean suportaRgb) {
        super(id, nome, marca, lumens, soquete);
        this.protocolo = protocolo;
        this.suportaRgb = suportaRgb;
    }

    @Override
    public String getTipo() { return "Smart LED"; }

    @Override
    public double calcularEficiencia() {
        double watts = getVariacoes().isEmpty() ? 1 : getVariacoes().get(0).getPotenciaWatts();
        return getLumens() / watts;
    }

    public String getProtocolo() { return protocolo; }
    public boolean isSuportaRgb() { return suportaRgb; }

    @Override
    public String toString() {
        return super.toString() + " | Protocolo: " + protocolo + " | RGB: " + (suportaRgb ? "Sim" : "Não");
    }
}
