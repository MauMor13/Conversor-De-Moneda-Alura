package models;
import enums.TypeCurrency;
import services.HttpClientService;

import java.time.LocalDate;

public class Conversion {
    private TypeCurrency baseCurrency;
    private TypeCurrency targetCurrency;
    private LocalDate dateCreate;
    private Double amountEntered;
    private Double amountResult;
    private HttpClientService httpClientService;

    public Conversion(TypeCurrency baseCurrency, TypeCurrency targetCurrency, Double amountEntered) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.dateCreate = LocalDate.now();
        this.amountEntered = amountEntered;
        this.amountResult = httpClientService.getConversionTwoCurrency(baseCurrency, targetCurrency, amountEntered);
    }
    public Conversion(HttpClientService httpClientService){
        this.httpClientService = httpClientService;
    }

    public TypeCurrency getBaseCurrency() {
        return baseCurrency;
    }

    public TypeCurrency getTargetCurrency() {
        return targetCurrency;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public Double getAmountEntered() {
        return amountEntered;
    }

    public Double getAmountResult() {
        return amountResult;
    }

    public void setBaseCurrency(TypeCurrency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setTargetCurrency(TypeCurrency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setAmountEntered(Double amountEntered) {
        this.amountEntered = amountEntered;
    }

    public void setAmountResult(Double amountResult) {
        this.amountResult = amountResult;
    }
}
