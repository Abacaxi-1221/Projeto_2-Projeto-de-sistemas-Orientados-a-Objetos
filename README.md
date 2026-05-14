# LumináRIA — Sistema de Gestão de Lâmpadas

Projeto acadêmico de Programação Orientada a Objetos — Período 2 | UniSenai  
**Entrega:** 21/05/2026 às 22h10

---

## Sobre o Projeto

Sistema orientado a objetos para gestão de um catálogo de lâmpadas com filtros técnicos, comparação de produtos e simulação de ciclo de pedido.

---

## Estrutura do Projeto

```
src/com/luminaria/
├── model/
│   ├── Lampada.java              ← Classe abstrata base
│   ├── LedLampada.java
│   ├── FluorescenteLampada.java
│   ├── IncandescenteLampada.java
│   ├── SmartLampada.java         ← Extensão via OCP (sem alterar código existente)
│   ├── VariacaoProduto.java      ← Composição: voltagem, cor, estoque, preço
│   └── enums/
│       ├── TipoSoquete.java
│       └── StatusPedido.java
├── catalogo/
│   ├── Catalogo.java             ← Busca com filtros técnicos
│   ├── FiltroCatalogo.java       ← Builder de filtros (lúmens, soquete, voltagem)
│   └── ComparadorLampadas.java   ← Comparação de até 3 lâmpadas
├── pedido/
│   ├── Pedido.java               ← Ciclo: Rascunho → Pago → Enviado
│   ├── ItemPedido.java
│   └── PedidoService.java
└── Main.java                     ← Demonstração completa
```

---

## Como Compilar e Executar

```bash
# A partir da raiz do projeto
javac -d out $(find src -name "*.java")
java -cp out com.luminaria.Main
```

---

## Conceitos OOP Aplicados

| Conceito | Aplicação |
|---|---|
| **Herança** | `LedLampada`, `FluorescenteLampada`, `IncandescenteLampada`, `SmartLampada` herdam de `Lampada` |
| **Abstração** | `Lampada` define `getTipo()` e `calcularEficiencia()` como métodos abstratos |
| **Composição** | `Lampada` possui `List<VariacaoProduto>` (composição, não herança) |
| **Encapsulamento** | Todos os atributos são `private` com acesso via getters |
| **Polimorfismo** | `calcularEficiencia()` com comportamento distinto por subtipo |
| **OCP** | `SmartLampada` foi adicionada sem modificar nenhuma classe existente |

---

## Diagramas UML

Os arquivos `.puml` na pasta `docs/uml/` podem ser visualizados em:
- [PlantUML Online](https://www.plantuml.com/plantuml/uml/) — cole o conteúdo do arquivo
- Plugin PlantUML no VS Code ou IntelliJ

| Diagrama | Arquivo |
|---|---|
| Casos de Uso | `docs/uml/casos-de-uso.puml` |
| Classes | `docs/uml/diagrama-classes.puml` |
| Sequência | `docs/uml/diagrama-sequencia.puml` |
| Estados | `docs/uml/diagrama-estados.puml` |

---

## Ciclo de Vida do Pedido

```
RASCUNHO ──▶ PAGO ──▶ ENVIADO
    │            │
    └────────────┴──▶ CANCELADO
```
