package com.concessionaria.dto;

import com.concessionaria.model.Condicao;
import com.concessionaria.model.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarroRequest(
        @Schema(description = "Modelo do carro", example = "Palio")
        @NotBlank(message = "Campo obrigatorio")
        String modelo,

        @Schema(description = "Marca do carro", example = "FIAT")
        @NotBlank(message = "Campo obrigatorio")
        String marca,

        @Schema(description = "Placa do carro", example = "AAA-1A11")
        @NotBlank(message = "Campo obrigatorio")
        String placa,

        @Schema(description = "Chassi do carro", example = "1AAA22BB333333")
        @NotBlank(message = "Campo obrigatorio")
        String chassi,

        @Schema(description = "Quilometragem do carro", example = "0km")
        @NotNull(message = "Campo obrigatorio")
        Integer kilometragem,

        @Schema(description = "Preço do carro", example = "R$100.000,00")
        @NotNull(message = "Campo obrigatorio")
        BigDecimal preco,

        @Schema(description = "Ano do modelo", example = "2021/2022")
        @NotBlank(message = "Campo obrigatorio")
        String dtModelo,


        @Schema(description = "Ano de fabricação", example = "2021")
        @NotBlank(message = "Campo obrigatorio")
        String dtFabricacao,
        @Schema(description = "Status do carro", example = "DISPONIVEL/RESERVADO/VENDIDO")
        @NotBlank(message = "Campo obrigatorio")
        Status status,

        @Schema(description = "Condição do carro", example = "NOVO/SEMINOVO")
        @NotBlank(message = "Campo obrigatorio")
        Condicao condicao
) {
}
