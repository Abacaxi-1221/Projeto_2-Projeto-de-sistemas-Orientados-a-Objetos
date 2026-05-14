package com.luminaria.model;

import com.luminaria.model.enums.TipoSoquete;

public class FluorescenteLampada extends Lampada {

    private final int irc;
    private final int temperaturaCorK;

    public FluorescenteLampada(String id, String nome, String marca, int lumens, TipoSoquete soquete,
                               int irc, int temperaturaCorK) {
        super(id, nome, marca, lumens, soquete);
        this.irc = irc;
        this.temperaturaCorK = temperaturaCorK;
    }

    @Override
    public String getTipo() { return "Fluorescente"; }

    @Override
    public double calcularEficiencia() {
        double watts = getVariacoes().isEmpty() ? 1 : getVariacoes().get(0).getPotenciaWatts();
        return (getLumens() / watts) * (irc / 100.0);
    }

    public int getIrc() { return irc; }
    public int getTemperaturaCorK() { return temperaturaCorK; }

    @Override
    public String toString() {
        return super.toString() + " | IRC: " + irc + " | Temp. cor: " + temperaturaCorK + "K";
    }
}
