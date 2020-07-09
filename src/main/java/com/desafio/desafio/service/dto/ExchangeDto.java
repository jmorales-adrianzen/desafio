package com.desafio.desafio.service.dto;

import com.desafio.desafio.domain.Exchange;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExchangeDto implements Serializable {

    private static final long serialVersionUID = 5461539344870300678L;

    @NotNull
    private Exchange exchange;

    public ExchangeDto(Exchange exchange) {
        this.exchange = exchange;
    }

}
