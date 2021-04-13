package ea.ea.services;

import ea.ea.repositories.CurrencyLayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeRatesServiceImplTests {
    @Mock
    CurrencyLayerRepository mockRepository;

    @InjectMocks
    ExchangeRatesServiceImpl mockService;

    @Test
    public void getExchangeRate_Should_Return_Rate() {

        //Arrange
        //Act
        mockService.getExchangeRate(anyString(),anyString());

        //Assert
        Mockito.verify(mockRepository,Mockito.times(1)).getExchangeRateData(anyString(),anyString());
    }

}
