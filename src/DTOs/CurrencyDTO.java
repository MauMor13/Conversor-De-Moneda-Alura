package DTOs;
import java.time.LocalDate;
import java.util.HashMap;

public record CurrencyDTO(  HashMap<String, Float> conversion_rates ,
                            LocalDate time_last_update_utc,
                            LocalDate time_next_update_utc) {}
