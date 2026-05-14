package com.luminaria.catalogo;

import com.luminaria.model.enums.TipoSoquete;

/** Builder de filtros para busca no catálogo. */
public class FiltroCatalogo {

    private Integer lumensMin;
    private Integer lumensMax;
    private TipoSoquete soquete;
    private Integer voltagem;
    private String tipo;

    public FiltroCatalogo lumensMin(int v) { this.lumensMin = v; return this; }
    public FiltroCatalogo lumensMax(int v) { this.lumensMax = v; return this; }
    public FiltroCatalogo soquete(TipoSoquete s) { this.soquete = s; return this; }
    public FiltroCatalogo voltagem(int v) { this.voltagem = v; return this; }
    public FiltroCatalogo tipo(String t) { this.tipo = t; return this; }

    public Integer getLumensMin() { return lumensMin; }
    public Integer getLumensMax() { return lumensMax; }
    public TipoSoquete getSoquete() { return soquete; }
    public Integer getVoltagem() { return voltagem; }
    public String getTipo() { return tipo; }
}
