package com.desafio.desafio.repository;

import com.desafio.desafio.domain.Exchange;
import com.neovisionaries.i18n.CurrencyCode;
import reactor.core.publisher.Mono;

public interface ExchangeRepository {

    Mono<Exchange> getExchange(CurrencyCode origin, CurrencyCode destiny);

    Mono<Integer> save(Exchange exchange);

}
