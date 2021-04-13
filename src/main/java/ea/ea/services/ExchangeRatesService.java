package ea.ea.services;

/**
 * Service layer - interface for Exchange Rate.
 */

public interface ExchangeRatesService {
    double getExchangeRate(String sourceCurrency, String targetCurrency);
}
