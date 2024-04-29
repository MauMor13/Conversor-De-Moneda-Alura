package services;
import DTOs.TypeCurrencyDTO;
import enums.TypeCurrency;
import models.Conversion;
import java.util.List;


public interface HttpClientService {
    TypeCurrencyDTO getTypeCurrencies();
    void getMultipleConversion(TypeCurrency typeCurrency);
    void getConversionTwoCurrency(TypeCurrency baseType, TypeCurrency targetType, double amount, List<Conversion> listConversionHistory);
}
