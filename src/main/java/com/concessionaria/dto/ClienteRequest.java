package com.concessionaria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @Schema(description = "Nome do cliente", example = "Claudio")
        @NotBlank(message = "Nome é obrigatorio")
         String nome,

        @Schema(description = "CPF do cliente", example = "11122233345")
        @NotBlank(message = "CPF é obrigatorio")
         String cpf,

        @Schema(description = "E-mail do cliente", example = "cliente@email.com")
        @NotBlank(message = "E-mail é obrigatorio")
         String email,

        @Schema(description = "11911223344")
        @NotBlank(message = "Telefone é obrigatorio")
         String telefone
) {
}
