package ea.ea.services;

import ea.ea.exceptions.EntityNotFoundException;
import ea.ea.models.Transaction;
import ea.ea.repositories.CurrencyLayerRepository;
import ea.ea.repositories.TransactionsRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service layer - implementation class for Transaction.
 */

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final TransactionsRepositoryJPA transactionsRepositoryJPA;
    private final CurrencyLayerRepository currencyLayerRepository;

    @Autowired
    public TransactionsServiceImpl(TransactionsRepositoryJPA transactionsRepository, CurrencyLayerRepository currencyLayerRepository) {
        this.transactionsRepositoryJPA = transactionsRepository;
        this.currencyLayerRepository = currencyLayerRepository;
    }

    @Override
    public List<Transaction> getAll() {
        return transactionsRepositoryJPA.findAll();
    }

    @Override
    public Page<Transaction> getAll(Pageable pageable) {
        return transactionsRepositoryJPA.findAll(pageable);
    }

    @Override
    public Transaction getById(int id) {
        return transactionsRepositoryJPA
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Transaction", id));
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        double exchangeRate = currencyLayerRepository
                .getExchangeRateData(
                        transaction.getExchangeRate().getSourceCurrency(),
                        transaction.getExchangeRate().getTargetCurrency());
        transaction.getExchangeRate().setConversionRate(exchangeRate);
        transaction.calculateTargetAmount();
        return transactionsRepositoryJPA.save(transaction);
    }

    @Override
    public List<Transaction> filterByCurrency(String currency) {
        return transactionsRepositoryJPA.findByExchangeRate_SourceCurrencyOOrExchangeRate_TargetCurrency(currency);
    }

    @Override
    public List<Transaction> sortByDateAndSourceCurrency() {
        return transactionsRepositoryJPA.findAllOrderByDateAndExchangeRate_SourceCurrency();
    }

    @Override
    public List<Transaction> filterByDate(Date date, Pageable pageable) {
        return transactionsRepositoryJPA.findAllByDate(date, pageable);
    }


}
