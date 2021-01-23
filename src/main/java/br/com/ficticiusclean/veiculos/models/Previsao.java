package br.com.ficticiusclean.veiculos.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Previsao {

    private BigDecimal precoGasolina;

    private BigDecimal kmCidade;

    private BigDecimal kmRodovia;
}
