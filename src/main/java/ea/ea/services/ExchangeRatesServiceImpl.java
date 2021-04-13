package ea.ea.services;

import ea.ea.repositories.CurrencyLayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer - implementation class for Exchange Rate.
 */

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {
    private final CurrencyLayerRepository currencyLayerRepository;

    @Autowired
    public ExchangeRatesServiceImpl(CurrencyLayerRepository currencyLayerRepository) {
        this.currencyLayerRepository = currencyLayerRepository;
    }

    @Override
    public double getExchangeRate(String sourceCurrency, String targetCurrency) {
        return currencyLayerRepository.getExchangeRateData(sourceCurrency, targetCurrency);
    }
}
