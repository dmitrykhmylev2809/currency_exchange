package ru.skillbox.currency.exchange.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.model.ValCurs;
import ru.skillbox.currency.exchange.model.Valute;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ScheduledRequest {

    @Value("${cbr.xmlUrl}")
    private String xmlUrl;

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    public ScheduledRequest(RestTemplate restTemplate, CurrencyRepository currencyRepository) {
        this.restTemplate = restTemplate;
        this.currencyRepository = currencyRepository;
    }


    @Scheduled(fixedRate = 360000)
    @Async
    public void bankRequest() {
        try {
            String xmlData = restTemplate.getForObject(xmlUrl, String.class);
            processXmlData(xmlData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processXmlData(String xmlData) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(new StringReader(xmlData));
        List<Currency> currencies = convertToCurrencies(valCurs);
        currencyRepository.deleteAll();
        currencyRepository.saveAll(currencies);
        log.info("Курсы валют обновлены - {}", LocalDateTime.now());
    }

    private List<Currency> convertToCurrencies(ValCurs valCurs) {
        return valCurs.getValutes().stream()
                .map(this::convertToCurrency)
                .collect(Collectors.toList());
    }

    private Currency convertToCurrency(Valute valute) {
        Currency currency = new Currency();
        currency.setName(valute.getName());
        currency.setNominal(valute.getNominal());
        currency.setValue(Double.parseDouble(valute.getValue().replace(",", ".")));
        currency.setIsoNumCode(valute.getNumCode());
        currency.setIsoCharCode(valute.getCharCode());
        return currency;
    }

}
