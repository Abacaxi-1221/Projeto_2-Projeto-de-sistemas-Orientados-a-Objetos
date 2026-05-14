package com.luminaria.model;

import com.luminaria.model.enums.TipoSoquete;

public class LedLampada extends Lampada {

    private final int vidaUtilHoras;
    private final boolean dimmerizavel;

    public LedLampada(String id, String nome, String marca, int lumens, TipoSoquete soquete,
                      int vidaUtilHoras, boolean dimmerizavel) {
        super(id, nome, marca, lumens, soquete);
        this.vidaUtilHoras = vidaUtilHoras;
        this.dimmerizavel = dimmerizavel;
    }

    @Override
    public String getTipo() { return "LED"; }

    @Override
    public double calcularEficiencia() {
        double watts = getVariacoes().isEmpty() ? 1 : getVariacoes().get(0).getPotenciaWatts();
        return getLumens() / watts;
    }

    public int getVidaUtilHoras() { return vidaUtilHoras; }
    public boolean isDimmerizavel() { return dimmerizavel; }

    @Override
    public String toString() {
        return super.toString() + " | Vida útil: " + vidaUtilHoras + "h | Dimerizável: " + (dimmerizavel ? "Sim" : "Não");
    }
}
