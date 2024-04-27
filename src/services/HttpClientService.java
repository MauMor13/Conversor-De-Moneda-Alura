package services;
import DTOs.ConversionPairDTO;
import DTOs.CurrencyDTO;
import DTOs.TypeCurrencyDTO;
import enums.TypeCurrency;
import models.Conversion;


public interface HttpClientService {
    TypeCurrencyDTO getTypeCurrencies();
    CurrencyDTO getCurrencyResponse(String typeCurrency);
    void getConversionTwoCurrency(TypeCurrency baseType, TypeCurrency targetType, double amount);
}
