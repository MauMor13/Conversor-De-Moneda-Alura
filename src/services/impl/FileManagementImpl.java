package services.impl;
import DTOs.ListConversionDTO;
import models.Conversion;
import services.FileManagementService;
import services.JsonTransformService;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManagementImpl implements FileManagementService {

    private final JsonTransformService jsonTransform = new JsonTransformImpl();
    private final String nameFile = "historyConversion.txt";
    private final Path routerFile = Paths.get(this.nameFile);

    public boolean existFileHistory(){
        return Files.exists(this.routerFile);
    }

    @Override
    public List<Conversion> readFileHistory(){
        if(this.existFileHistory()){
            try{
                BufferedReader br = new BufferedReader(new FileReader(nameFile));
                    StringBuilder contenido = new StringBuilder();
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        contenido.append(linea);
                    }
                    if (!contenido.isEmpty()){
                        ListConversionDTO listConversionDTO = jsonTransform.deserializationGson(contenido.toString(), ListConversionDTO.class);
                        if (listConversionDTO != null){
                            return listConversionDTO.arrayListConversion();
                        }
                    }
            }catch (IOException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void writeFileHistory(List<Conversion> conversionArrayList){
        if (this.existFileHistory()){
            try {
                Files.delete(this.routerFile);
            } catch (IOException e) {
                System.out.println("Error al eliminar el archivo: " + e.getMessage());
            }
        }
        try {
            FileWriter newFileHistory = new FileWriter(this.nameFile);
            newFileHistory.write(this.jsonTransform.serializationGson(new ListConversionDTO(conversionArrayList)));
            newFileHistory.close();
        }catch (IOException e){
            System.out.println("Ocurrió un error al guardar el historial: " + e.getMessage());
        }
    }

}
