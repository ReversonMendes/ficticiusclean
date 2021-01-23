package br.com.ficticiusclean.veiculos.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VeiculoRanking {

    private String nome;

    private String marca;

    private String modelo;

    private int ano;

    private BigDecimal qtdCombustivelGasto;

    private BigDecimal valorTotalGasto;
}
