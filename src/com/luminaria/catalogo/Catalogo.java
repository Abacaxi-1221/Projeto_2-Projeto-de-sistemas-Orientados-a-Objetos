package com.luminaria.catalogo;

import com.luminaria.model.Lampada;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Catalogo {

    private final List<Lampada> lampadas = new ArrayList<>();

    public void adicionar(Lampada l) { lampadas.add(l); }

    public List<Lampada> listarTodos() { return lampadas; }

    public Optional<Lampada> buscarPorId(String id) {
        return lampadas.stream().filter(l -> l.getId().equals(id)).findFirst();
    }

    public List<Lampada> buscar(FiltroCatalogo f) {
        return lampadas.stream()
            .filter(l -> f.getLumensMin() == null || l.getLumens() >= f.getLumensMin())
            .filter(l -> f.getLumensMax() == null || l.getLumens() <= f.getLumensMax())
            .filter(l -> f.getSoquete() == null || l.getSoquete() == f.getSoquete())
            .filter(l -> f.getTipo() == null || l.getTipo().equalsIgnoreCase(f.getTipo()))
            .filter(l -> f.getVoltagem() == null || l.getVariacaoPorVoltagem(f.getVoltagem()).isPresent())
            .collect(Collectors.toList());
    }
}
