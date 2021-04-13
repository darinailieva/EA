package ea.ea.controllers;

import ea.ea.services.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for retrieving exchange rate.
 */

@RestController
@RequestMapping("/api")
public class ExchangeRateController {

    private final ExchangeRatesService exchangeRatesService;

    @Autowired
    public ExchangeRateController(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/exchange/rate")
    public double getExchangeRate(@RequestParam String sourceCurrency, @RequestParam String targetCurrency) {
        return exchangeRatesService.getExchangeRate(sourceCurrency, targetCurrency);
    }
}

