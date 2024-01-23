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
@JsonView(CurrencyViews.CurrencySummaryView.class)
public class CurrencyDto {
    private Long id;

    private String name;

    private Long nominal;

    private Double value;

    private Long isoNumCode;

    private String isoCharCode;

    @JsonView(CurrencyViews.CurrencyDetailView.class)
    public Long getId() {
        return id;
    }

    @JsonView(CurrencyViews.CurrencyDetailView.class)
    public Long getNominal() {
        return nominal;
    }

    @JsonView(CurrencyViews.CurrencyDetailView.class)
    public Long getIsoNumCode() {
        return isoNumCode;
    }

    @JsonView(CurrencyViews.CurrencyDetailView.class)
    public String getIsoCharCode() {
        return isoCharCode;
    }
}