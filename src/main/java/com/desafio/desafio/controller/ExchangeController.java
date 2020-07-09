package com.desafio.desafio.controller;

import com.desafio.desafio.controller.dto.ExchangeWebDto;
import com.desafio.desafio.core.exceptions.CurrencyNotFoundException;
import com.desafio.desafio.service.ExchangeService;
import com.neovisionaries.i18n.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
@RequestMapping("/exchanges")
@AllArgsConstructor
@Slf4j
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping(path = "/{origin}/destino/{destiny}")
    public Mono<ResponseEntity<ExchangeWebDto>> get(@PathVariable(name = "origin") CurrencyCode origin,
                                                    @PathVariable(name = "destiny") CurrencyCode destiny,
                                                    @RequestParam(value = "amount") double amount){
        return exchangeService.getCambio(origin,destiny,amount)
                .map(dto-> ResponseEntity.ok(new ExchangeWebDto(dto.getExchange())))
                .onErrorResume(throwable -> throwable instanceof CurrencyNotFoundException,
                        throwable -> Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public Mono<ResponseEntity<ExchangeWebDto>> create(@Valid @RequestBody ExchangeWebDto exchangeWebDto) {
        return exchangeService.grabar(exchangeWebDto.getExchange())
                .map(dto-> ResponseEntity.ok(new ExchangeWebDto(exchangeWebDto.getExchange())));
    }

}
