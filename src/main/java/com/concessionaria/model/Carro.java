package com.concessionaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

@Table(name = "Carro")

public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String marca;

    @Column(unique = true)
    private String placa;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false, unique = true)
    private String chassi;

    @Column(nullable = false)
    private Integer kilometragem;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private String dtModelo;

    @Column(nullable = false)
    private String dtFabricacao;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Condicao condicao;
}
