package services;

import models.Conversion;

public interface JsonTransformService {
    <T> T deserializationGson(String json, Class<T> typeClass);
    String serializationGson(Conversion conversion);
}
