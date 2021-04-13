package ea.ea.repositories;

import ea.ea.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * JPA repository for Transactions.
 */

public interface TransactionsRepositoryJPA extends JpaRepository<Transaction, Integer> {
    @Query("FROM Transaction t WHERE t.exchangeRate.sourceCurrency =:currency " +
            "OR t.exchangeRate.targetCurrency =:currency")
    List<Transaction> findByExchangeRate_SourceCurrencyOOrExchangeRate_TargetCurrency(String currency);

    @Query("FROM Transaction t ORDER BY t.date DESC, t.exchangeRate.sourceCurrency")
    List<Transaction> findAllOrderByDateAndExchangeRate_SourceCurrency();

    Page<Transaction> findAll(Pageable pageable);

    List<Transaction> findAllByDate(Date date, Pageable pageable);
}
