package DTOs;
import java.util.HashMap;

public record MultiCurrencyConversionDTO(HashMap<String, Double> conversion_rates,
                                         String base_code) {}
