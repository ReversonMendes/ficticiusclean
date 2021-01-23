package br.com.ficticiusclean.veiculos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
public class Veiculo {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{nome.not.blank}")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "{marca.not.blank}")
    @Column(nullable = false)
    private String marca;

    @NotNull(message = "{modelo.not.blank}")
    @Column(nullable = false)
    private String modelo;

    @NotNull(message = "{dataFabricacao.not.blank}")
    @Past(message = "{dataFabricacao.not.invalid}")
    @Column(nullable = false)
    private Date dataFabricacao;

    @NotNull(message = "{consumoCidade.not.blank}")
    @Column(nullable = false)
    private BigDecimal consumoCidade;

    @NotNull(message = "{consumoRodovia.not.blank}")
    @Column(nullable = false)
    private BigDecimal consumoRodovia;
}
