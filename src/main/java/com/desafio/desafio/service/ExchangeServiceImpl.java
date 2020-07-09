package com.desafio.desafio.service;

import com.desafio.desafio.domain.Exchange;
import com.desafio.desafio.repository.ExchangeRepository;
import com.desafio.desafio.service.dto.ExchangeDto;
import com.neovisionaries.i18n.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeRepository repository;

    @Override
    public Mono<ExchangeDto> getCambio(CurrencyCode origin, CurrencyCode destiny, double amount) {
        return repository.getExchange(origin, destiny)
                .map(entity -> {
                    ExchangeDto dto = new ExchangeDto(entity);
                    dto.getExchange().setAmount(amount);
                    return dto;
                })
                .map(this::calcular);
    }

    public ExchangeDto calcular(final ExchangeDto exchangeDto) {
        Exchange exchange = exchangeDto.getExchange();
        exchange.setAmountExchange(exchange.getAmount() * exchange.getExchangeRate());
        return exchangeDto;
    }

    @Override
    public Mono<Integer> grabar(Exchange exchange) {
        return repository.save(exchange);
    }

}
