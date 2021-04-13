package ea.ea.services;

import ea.ea.exceptions.EntityNotFoundException;
import ea.ea.models.ExchangeRate;
import ea.ea.models.Transaction;
import ea.ea.repositories.CurrencyLayerRepository;
import ea.ea.repositories.TransactionsRepositoryJPA;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionsServiceImplTests {

    @Mock
    TransactionsRepositoryJPA mockTransactionsRepositoryJPA;
    @Mock
    CurrencyLayerRepository mockCurrencyLayerRepository;

    @InjectMocks
    TransactionsServiceImpl mockService;

    Pageable mockPageable = PageRequest.of(0, 2);

    Date mockDate = new Date(2021- 4 -10);

    @Test
    public void getAll_should_returnEmptyList() {
        //Arrange
        Mockito.when(mockTransactionsRepositoryJPA.findAll())
                .thenReturn(new ArrayList<>());

        //Act
        mockService.getAll();

        //Assert
        Assert.assertTrue(mockTransactionsRepositoryJPA.findAll().isEmpty());
    }

    @Test
    public void getAll_should_returnEmptyPage() {
        //Arrange
        Mockito.when(mockTransactionsRepositoryJPA.findAll(mockPageable))
                .thenReturn(Page.empty());

        //Act
        mockService.getAll(mockPageable);

        //Assert
        Assert.assertTrue(mockTransactionsRepositoryJPA.findAll(mockPageable).isEmpty());
    }

    @Test
    public void getById_should_returnTransaction_whenTransactionExists() {
        //Arrange
        Transaction expectedTransaction = new Transaction();

        Mockito.when(mockTransactionsRepositoryJPA.findById(anyInt()))
                .thenReturn(java.util.Optional.of(expectedTransaction));

        //Act
        Transaction returnedTransaction = mockService.getById(anyInt());

        //Assert
        Assert.assertSame(expectedTransaction, returnedTransaction);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getById_throws_whenTransactionDoesNotExist() {
        //Act
        mockService.getById(anyInt());
    }

    @Test
    public void createTransaction_should_addInRepository() {
        //Arrange
        Transaction transaction = new Transaction(5, new ExchangeRate("USD", "BGN"));
        Mockito.when(mockCurrencyLayerRepository.getExchangeRateData(anyString(), anyString())).thenReturn(anyDouble());
        transaction.getExchangeRate().setConversionRate(anyDouble());
        transaction.calculateTargetAmount();

        //Act
        mockService.createTransaction(transaction);

        //Assert
        Mockito.verify(mockTransactionsRepositoryJPA, Mockito.times(1)).save(transaction);
    }

    @Test
    public void filterByCurrency_should_returnFromRepository() {
        //Arrange, Act
        mockService.filterByCurrency(anyString());

        //Assert
        Mockito.verify(mockTransactionsRepositoryJPA, Mockito.times(1)).findByExchangeRate_SourceCurrencyOOrExchangeRate_TargetCurrency(anyString());
    }

    @Test
    public void sortByDateAndSourceCurrency_should_returnFromRepository() {
        //Arrange, Act
        mockService.sortByDateAndSourceCurrency();

        //Assert
        Mockito.verify(mockTransactionsRepositoryJPA, Mockito.times(1)).findAllOrderByDateAndExchangeRate_SourceCurrency();
    }


    @Test
    public void filterByDate_should_returnFromRepository() {
        //Arrange, Act
        mockService.filterByDate(mockDate, mockPageable);

        //Assert
        Mockito.verify(mockTransactionsRepositoryJPA, Mockito.times(1)).findAllByDate(mockDate, mockPageable);
    }
}
