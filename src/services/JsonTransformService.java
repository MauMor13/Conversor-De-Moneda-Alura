package services;
import DTOs.ListConversionDTO;
import models.Conversion;
import java.util.List;

public interface JsonTransformService {
    <T> T deserializationGson(String json, Class<T> typeClass);
    String serializationGson(ListConversionDTO conversionArrayList);
}
