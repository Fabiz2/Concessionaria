package com.concessionaria.dto;

public record ClienteResponse(
        Integer id,

        String nome,

         String cpf,

        String email,

        String telefone
        )
{
}
