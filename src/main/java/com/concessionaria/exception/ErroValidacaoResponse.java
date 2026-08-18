package com.concessionaria.exception;

import java.util.List;

public record ErroValidacaoResponse(Integer status, List<ErroCampo> erroCampos) {
}
