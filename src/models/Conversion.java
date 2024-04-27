package models;
import DTOs.ConversionPairDTO;
import enums.TypeCurrency;
import services.HttpClientService;
import services.impl.HttpClientImpl;

import java.time.LocalDate;

public class Conversion {

    private TypeCurrency baseCurrency;
    private TypeCurrency targetCurrency;
    private LocalDate dateCreate;
    private Double amountEntered;
    private Double amountResult;

    public Conversion(ConversionPairDTO conversionPairDTO, Double amountEntered) {
        this.baseCurrency = TypeCurrency.valueOf(conversionPairDTO.base_code());
        this.targetCurrency = TypeCurrency.valueOf(conversionPairDTO.target_code());
        this.dateCreate = LocalDate.now();
        this.amountEntered = amountEntered;
        this.amountResult = conversionPairDTO.conversion_result();
    }
    public Conversion() { }

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
