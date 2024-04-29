package services.impl;
import DTOs.ListConversionDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import models.Conversion;
import services.JsonTransformService;
import java.util.List;

public class JsonTransformImpl implements JsonTransformService {

    //instancia del objeto gson para el uso
    private final Gson gson = new Gson();

    //metodo deserializacion del json
    @Override
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

    //metodo de serializacion del json
    @Override
    public String serializationGson(ListConversionDTO conversionArrayList){
        if (conversionArrayList.arrayListConversion().isEmpty()){
            System.out.println("Esta conversion no contiene datos");
            return null;
        }
        return this.gson.toJson(conversionArrayList);
    }

}
