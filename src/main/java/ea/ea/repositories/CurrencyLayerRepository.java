package ea.ea.repositories;

/**
 * Interface for repository of Currency Layer.
 */

public interface CurrencyLayerRepository {
    double getExchangeRateData(String sourceCurrency, String targetCurrency);
}
