package com.luminaria.catalogo;

import com.luminaria.model.Lampada;
import com.luminaria.model.VariacaoProduto;
import java.util.List;
import java.util.stream.Collectors;

public class ComparadorLampadas {

    public String comparar(List<Lampada> lampadas) {
        if (lampadas == null || lampadas.isEmpty()) throw new IllegalArgumentException("Informe ao menos 1 lâmpada.");
        if (lampadas.size() > 3) throw new IllegalArgumentException("Máximo de 3 lâmpadas para comparação.");

        StringBuilder sb = new StringBuilder();
        String linha = "+-----------------------+--------------------+--------------------+--------------------+\n";
        String fmt   = "| %-21s | %-18s | %-18s | %-18s |\n";

        String[] nomes = row(lampadas, l -> truncate(l.getNome(), 18));
        String[] tipos = row(lampadas, l -> truncate(l.getTipo(), 18));
        String[] lumens = row(lampadas, l -> l.getLumens() + " lm");
        String[] soquetes = row(lampadas, l -> l.getSoquete().toString());
        String[] efic = row(lampadas, l -> String.format("%.1f lm/W", l.calcularEficiencia()));
        String[] volt = row(lampadas, l ->
            l.getVariacoes().stream().map(v -> v.getVoltagem() + "V").collect(Collectors.joining("/")));
        String[] preco = row(lampadas, l ->
            l.getVariacoes().stream().map(v -> "R$" + v.getPreco()).collect(Collectors.joining("/")));

        sb.append(linha);
        sb.append(String.format(fmt, "Atributo", nomes[0], nomes[1], nomes[2]));
        sb.append(linha);
        sb.append(String.format(fmt, "Tipo", tipos[0], tipos[1], tipos[2]));
        sb.append(String.format(fmt, "Lumens", lumens[0], lumens[1], lumens[2]));
        sb.append(String.format(fmt, "Soquete", soquetes[0], soquetes[1], soquetes[2]));
        sb.append(String.format(fmt, "Eficiência", efic[0], efic[1], efic[2]));
        sb.append(String.format(fmt, "Voltagens", volt[0], volt[1], volt[2]));
        sb.append(String.format(fmt, "Preço", preco[0], preco[1], preco[2]));
        sb.append(linha);

        return sb.toString();
    }

    private String[] row(List<Lampada> list, java.util.function.Function<Lampada, String> fn) {
        String[] r = {"—", "—", "—"};
        for (int i = 0; i < list.size(); i++) r[i] = fn.apply(list.get(i));
        return r;
    }

    private String truncate(String s, int max) {
        return s.length() > max ? s.substring(0, max - 1) + "…" : s;
    }
}
