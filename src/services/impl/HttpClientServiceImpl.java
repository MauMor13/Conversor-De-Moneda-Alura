package services.impl;
import DTOs.ConversionPairDTO;
import DTOs.CurrencyDTO;
import DTOs.TypeCurrencyDTO;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import enums.TypeCurrency;
import models.Conversion;
import services.HttpClientService;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientServiceImpl implements HttpClientService {

    private final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();

    public String requestStructure( String endUrl){
        //ruta y clave a la api de conversion
        String partialUrl = "https://v6.exchangerate-api.com/v6/";
        String keyRequest = "7778febf8a4041d98d88e830";
        //ruta completa
        String urlRequest = partialUrl.concat(keyRequest.concat(endUrl));
        //instancia de cliente para realizar la peticion
        HttpClient client =HttpClient.newHttpClient();
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlRequest))
                    .build();
            HttpResponse<String> response = client
                    .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .join();
            return response.body();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public <T> T deserializationGson(String json, Class<T> typeClass){
        //confirma que la propiedad result se encuentre
        JsonObject jsonObject = this.gson.fromJson(json, JsonObject.class);
        if (jsonObject.has("result")){
            String result = jsonObject.get("result").getAsString();
            if (result.equals("error")) {
                System.out.println("Error : " + jsonObject.get("error-type").getAsString());
                return null;
            }
        }
        return this.gson.fromJson(json, typeClass);
    }

    @Override
    public String serializationGson(Conversion conversion){
        if (conversion == null){
            System.out.println("Esta conversion no contiene datos");
            return null;
        }
        return this.gson.toJson(conversion);
    }

    @Override
    public TypeCurrencyDTO getTypeCurrencies() {
        String endUrl = "/codes";
        String json = this.requestStructure(endUrl);
        return this.deserializationGson(json, TypeCurrencyDTO.class);
    }

    @Override
    public CurrencyDTO getCurrencyResponse(String typeCurrency) {
        String endUrl = "/latest/ USD";
        String json = this.requestStructure(endUrl);
        return this.deserializationGson(json, CurrencyDTO.class);
    }

    @Override
    public ConversionPairDTO getConversionTwoCurrency(TypeCurrency baseType, TypeCurrency targetType, double amount) {
        String endUrl = "/pair/".concat(baseType.name().concat("/".concat(targetType.name().concat("/".concat(String.valueOf(amount))))));
        String json = this.requestStructure(endUrl);
        return this.deserializationGson(json, ConversionPairDTO.class);
    }
}
