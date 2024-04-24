package services.impl;
import DTOs.ConversionPairDTO;
import DTOs.CurrencyDTO;
import DTOs.TypeCurrencyDTO;
import enums.TypeCurrency;
import services.HttpClientService;
import services.JsonTransformService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientImpl implements HttpClientService {

    private final JsonTransformService jsonTransform;

    public HttpClientImpl(JsonTransformService jsonTransform) {
        this.jsonTransform = jsonTransform;
    }

    public String requestStructure(String endUrl){
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

    @Override
    public TypeCurrencyDTO getTypeCurrencies() {
        String endUrl = "/codes";
        String json = this.requestStructure(endUrl);
        return this.jsonTransform.deserializationGson(json, TypeCurrencyDTO.class);
    }

    @Override
    public CurrencyDTO getCurrencyResponse(String typeCurrency) {
        String endUrl = "/latest/ USD";
        String json = this.requestStructure(endUrl);
        return this.jsonTransform.deserializationGson(json, CurrencyDTO.class);
    }

    @Override
    public ConversionPairDTO getConversionTwoCurrency(TypeCurrency baseType, TypeCurrency targetType, double amount) {
        String endUrl = "/pair/".concat(baseType.name().concat("/".concat(targetType.name().concat("/".concat(String.valueOf(amount))))));
        String json = this.requestStructure(endUrl);
        return this.jsonTransform.deserializationGson(json, ConversionPairDTO.class);
    }
}
