package services;
import DTOs.TypeCurrencyDTO;
import enums.TypeCurrency;


public interface HttpClientService {
    TypeCurrencyDTO getTypeCurrencies();
    void getMultipleConversion(TypeCurrency typeCurrency);
    void getConversionTwoCurrency(TypeCurrency baseType, TypeCurrency targetType, double amount);
}
