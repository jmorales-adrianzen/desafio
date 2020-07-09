package com.desafio.desafio.service;

import com.desafio.desafio.domain.Exchange;
import com.desafio.desafio.service.dto.ExchangeDto;
import com.neovisionaries.i18n.CurrencyCode;
import reactor.core.publisher.Mono;

public interface ExchangeService {

    Mono<ExchangeDto> getCambio(CurrencyCode origin, CurrencyCode destiny, double amount);

    ExchangeDto calcular(final ExchangeDto exchangeDto);

    Mono<Integer> grabar(Exchange exchange);

}
