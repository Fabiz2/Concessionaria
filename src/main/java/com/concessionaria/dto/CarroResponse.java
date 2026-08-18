package com.concessionaria.dto;

import com.concessionaria.model.Condicao;
import com.concessionaria.model.Status;

import java.math.BigDecimal;

public record CarroResponse(
        Integer id,
        String modelo,
        String marca,
        String placa,
        String cor,
        String chassi,
        Integer kilometragem,
        BigDecimal preco,
        String dtModelo,
        String dtFabricacao,
        Status status,
        Condicao condicao
) {
}
