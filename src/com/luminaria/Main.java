package com.luminaria;

import com.luminaria.catalogo.Catalogo;
import com.luminaria.catalogo.ComparadorLampadas;
import com.luminaria.catalogo.FiltroCatalogo;
import com.luminaria.model.*;
import com.luminaria.model.enums.TipoSoquete;
import com.luminaria.pedido.Pedido;
import com.luminaria.pedido.PedidoService;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // ── 1. CRIAÇÃO DAS LÂMPADAS ────────────────────────────────────────────
        System.out.println("=== LUMINÁRIA - Sistema de Gestão de Lâmpadas ===\n");

        LedLampada led = new LedLampada("L001", "LED Slim 9W", "Philips", 810, TipoSoquete.E27, 25000, true);
        led.adicionarVariacao(new VariacaoProduto(127, "Branco Frio", 9, 50, new BigDecimal("18.90")));
        led.adicionarVariacao(new VariacaoProduto(220, "Branco Quente", 9, 30, new BigDecimal("18.90")));

        FluorescenteLampada fluor = new FluorescenteLampada("L002", "Compacta 15W", "Osram", 900, TipoSoquete.E27, 82, 4000);
        fluor.adicionarVariacao(new VariacaoProduto(127, "Neutro", 15, 20, new BigDecimal("12.50")));

        IncandescenteLampada incand = new IncandescenteLampada("L003", "Incandescente 60W", "GE", 830, TipoSoquete.E27, "Argônio");
        incand.adicionarVariacao(new VariacaoProduto(127, "Branco Quente", 60, 100, new BigDecimal("4.00")));
        incand.adicionarVariacao(new VariacaoProduto(220, "Branco Quente", 60, 80, new BigDecimal("4.00")));

        // OCP: SmartLampada adicionada SEM modificar nenhuma classe existente
        SmartLampada smart = new SmartLampada("L004", "Smart Bulb RGB", "Intelbras", 800, TipoSoquete.E27, "WiFi", true);
        smart.adicionarVariacao(new VariacaoProduto(127, "RGB", 10, 15, new BigDecimal("89.90")));

        // ── 2. CATÁLOGO ────────────────────────────────────────────────────────
        System.out.println("--- CATÁLOGO COMPLETO ---");
        Catalogo catalogo = new Catalogo();
        catalogo.adicionar(led);
        catalogo.adicionar(fluor);
        catalogo.adicionar(incand);
        catalogo.adicionar(smart);
        catalogo.listarTodos().forEach(System.out::println);

        // ── 3. FILTROS ─────────────────────────────────────────────────────────
        System.out.println("\n--- FILTRO: LED com 127V ---");
        FiltroCatalogo filtro = new FiltroCatalogo().tipo("LED").voltagem(127);
        catalogo.buscar(filtro).forEach(System.out::println);

        System.out.println("\n--- FILTRO: Lúmens entre 800 e 850, soquete E27 ---");
        FiltroCatalogo filtro2 = new FiltroCatalogo().lumensMin(800).lumensMax(850).soquete(TipoSoquete.E27);
        catalogo.buscar(filtro2).forEach(System.out::println);

        // ── 4. COMPARAÇÃO ─────────────────────────────────────────────────────
        System.out.println("\n--- COMPARAÇÃO LADO A LADO ---");
        ComparadorLampadas comp = new ComparadorLampadas();
        System.out.print(comp.comparar(List.of(led, fluor, incand)));

        // ── 5. CICLO DE PEDIDO ────────────────────────────────────────────────
        System.out.println("\n--- CICLO DE PEDIDO: Rascunho → Pago → Enviado ---");
        PedidoService service = new PedidoService();

        Pedido pedido = service.criarPedido("João Silva");
        System.out.println("Pedido criado: #" + pedido.getId() + " | Status: " + pedido.getStatus());

        service.adicionarItem(pedido, led, led.getVariacaoPorVoltagem(127).get(), 2);
        service.adicionarItem(pedido, smart, smart.getVariacaoPorVoltagem(127).get(), 1);
        System.out.println(pedido);

        service.confirmarPagamento(pedido);
        service.registrarEnvio(pedido);

        System.out.println("\nEstado final do pedido:");
        System.out.println(pedido);
    }
}
