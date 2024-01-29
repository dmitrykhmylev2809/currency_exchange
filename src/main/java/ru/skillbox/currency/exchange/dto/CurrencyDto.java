package ru.skillbox.currency.exchange.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.currency.exchange.view.CurrencyViews;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto {

    @JsonView(CurrencyViews.CurrencyDetailView.class)
    private Long id;

    @JsonView(CurrencyViews.CurrencySummaryView.class)
    private String name;

    @JsonView(CurrencyViews.CurrencyDetailView.class)
    private Long nominal;

    @JsonView(CurrencyViews.CurrencySummaryView.class)
    private Double value;

    @JsonView(CurrencyViews.CurrencyDetailView.class)
    private Long isoNumCode;

    @JsonView(CurrencyViews.CurrencyDetailView.class)
    private String isoCharCode;


}