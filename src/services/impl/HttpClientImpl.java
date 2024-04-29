package services.impl;
import DTOs.ConversionPairDTO;
import DTOs.MultiCurrencyConversionDTO;
import DTOs.TypeCurrencyDTO;
import enums.TypeCurrency;
import models.Conversion;
import services.HttpClientService;
import services.JsonTransformService;
import utilitis.Util;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpClientImpl implements HttpClientService {

    private final JsonTransformService jsonTransform = new JsonTransformImpl();

    //estructura de la peticion general
    public String requestStructure(String endUrl){
        //ruta y clave a la api de conversion
        String partialUrl = "https://v6.exchangerate-api.com/v6/";
        String keyRequest = "7778febf8a4041d98d88e830";

        //ruta completa
        String urlRequest = partialUrl.concat(keyRequest.concat(endUrl));

        //instancia de cliente para realizar la petición
        HttpClient client = HttpClient.newHttpClient();
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

    //retorno de los codigos que soporta la api
    @Override
    public TypeCurrencyDTO getTypeCurrencies() {
        String endUrl = "/codes";
        String json = this.requestStructure(endUrl);
        if (json != null){
            return this.jsonTransform.deserializationGson(json, TypeCurrencyDTO.class);
        }
        return null;
    }

    //manejo de la peticion de conversion de un valor en las demás tipos de moneda
    @Override
    public void getMultipleConversion(TypeCurrency typeCurrency) {
        String endUrl = "/latest/".concat(typeCurrency.name());
        String json = this.requestStructure(endUrl);
        if (json != null){
            MultiCurrencyConversionDTO multiCurrencyConversionDTO = this.jsonTransform.deserializationGson(json, MultiCurrencyConversionDTO.class);
            if (multiCurrencyConversionDTO != null){
                Util.printingCurrencyTypes(multiCurrencyConversionDTO.conversion_rates(),"Aquí podrá ver la conversion de las monedas respecto a un " + multiCurrencyConversionDTO.base_code());
            }
        }
        else {
            System.out.println("A ocurrido un error vuelva a intentarlo");
        }
    }

    //manejo de conversion entre dos monedas
    @Override
    public void getConversionTwoCurrency(TypeCurrency baseType, TypeCurrency targetType, double amount, List<Conversion> listConversionHistory) {
        String endUrl = "/pair/".concat(baseType.name().concat("/".concat(targetType.name().concat("/".concat(String.valueOf(amount))))));
        String json = this.requestStructure(endUrl);
        if (json != null){
            ConversionPairDTO conversionPairDTO = this.jsonTransform.deserializationGson(json, ConversionPairDTO.class);
            if (conversionPairDTO != null){
                Conversion conversion = new Conversion(conversionPairDTO, amount);
                listConversionHistory.add(conversion);//guardado en el historial de convesiones
                System.out.println("El resultado de la conversion de:\n" + "$ " +
                        Util.formatNumber(conversion.getAmountEntered()) + " " + baseType.name() +
                        " es de $ " + Util.formatNumber(conversion.getAmountResult()) + " " + targetType.name());
                System.out.println("*** Si desea puede ver sus ultimas conversiones en el historial ***\n");
            }
        }
        else {
            System.out.println("A ocurrido un error vuelva a intentarlo");
        }
    }

}
