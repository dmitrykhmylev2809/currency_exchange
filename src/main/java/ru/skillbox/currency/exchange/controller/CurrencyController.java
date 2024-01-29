package ru.skillbox.currency.exchange.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.request.ScheduledRequest;
import ru.skillbox.currency.exchange.service.CurrencyService;
import ru.skillbox.currency.exchange.view.CurrencyViews;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/currency")
public class CurrencyController {
    private final CurrencyService service;
    private final ScheduledRequest scheduledRequest;

    @JsonView(CurrencyViews.CurrencySummaryView.class)
    @GetMapping
    ResponseEntity<List<CurrencyDto>> getAllCurrencies() {
        return ResponseEntity.ok(service.getAllCurrencies());
    }

    @JsonView(CurrencyViews.CurrencyDetailView.class)
    @GetMapping(value = "/{id}")
    ResponseEntity<CurrencyDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }


    @JsonView(CurrencyViews.CurrencyDetailView.class)
    @GetMapping(value = "/convert")
    ResponseEntity<Double> convertValue(@RequestParam("value") Long value, @RequestParam("numCode") Long numCode) {
        return ResponseEntity.ok(service.convertValue(value, numCode));
    }

    @JsonView(CurrencyViews.CurrencyDetailView.class)
    @PostMapping("/create")
    ResponseEntity<CurrencyDto> create(@RequestBody CurrencyDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }
}
