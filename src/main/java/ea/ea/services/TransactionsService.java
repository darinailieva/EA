package ea.ea.services;

import ea.ea.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service layer - interface for Transaction.
 */

public interface TransactionsService {
    List<Transaction> getAll();
    Page<Transaction> getAll(Pageable pageable);
    Transaction getById(int id);
    Transaction createTransaction(Transaction transaction);
    List<Transaction> filterByCurrency(String currency);
    List<Transaction> sortByDateAndSourceCurrency();
    List<Transaction> filterByDate(Date date, Pageable pageable);
}
