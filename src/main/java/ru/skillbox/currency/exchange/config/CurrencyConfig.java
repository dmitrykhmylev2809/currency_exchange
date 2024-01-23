package ru.skillbox.currency.exchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;
import ru.skillbox.currency.exchange.request.ScheduledRequest;

@Configuration
@EnableScheduling
@EnableAsync
public class CurrencyConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ScheduledRequest scheduledRequest(RestTemplate restTemplate, CurrencyRepository currencyRepository) {
        return new ScheduledRequest(restTemplate, currencyRepository);
    }
}
