package com.luminaria.model;

import com.luminaria.model.enums.TipoSoquete;

public class IncandescenteLampada extends Lampada {

    private final String tipoGas;

    public IncandescenteLampada(String id, String nome, String marca, int lumens, TipoSoquete soquete,
                                String tipoGas) {
        super(id, nome, marca, lumens, soquete);
        this.tipoGas = tipoGas;
    }

    @Override
    public String getTipo() { return "Incandescente"; }

    @Override
    public double calcularEficiencia() {
        double watts = getVariacoes().isEmpty() ? 1 : getVariacoes().get(0).getPotenciaWatts();
        return getLumens() / watts;
    }

    public String getTipoGas() { return tipoGas; }

    @Override
    public String toString() {
        return super.toString() + " | Gás: " + tipoGas;
    }
}
