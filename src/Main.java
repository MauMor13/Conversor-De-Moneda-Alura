import enums.TypeCurrency;
import models.Conversion;
import services.HttpClientService;
import services.impl.HttpClientImpl;
import utilitis.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //instancia del historial de conversiones
        List<Conversion> listConversionHistory = new ArrayList<>();

        //instancia de servicio
        HttpClientService httpClientService = new HttpClientImpl();

        //var de tipo de modena local
        TypeCurrency localTypeCurrency;

        //instancia de lectura de entrada
        Scanner scn = new Scanner(System.in);

        //creacion de un menu de usuario
        System.out.println("******************** Conversion de Monedas ********************\n");
        Util.mainMenu();

        //booleano de salida de app
        boolean exit = false;

        do {
            byte numSelection = scn.nextByte();
            switch (numSelection){
                case 1:{

                    break;
                }
                case 2:{

                    break;
                }
                case 3:{
                    System.out.println("** Ingrese el monto a convertir:");
                    double amount = Util.entryAmountCheck();
                    TypeCurrency baseTypeCurrency = Util.controlCurrencyType(" ** Ingrese el tipo de cambio de este monto " + amount + "\n ** Ejemplo: USD - ARS - EUR");
                    TypeCurrency targetTypeCurrency = Util.controlCurrencyType("Por ultimo ingrese el tipo de cambio al cual desea convertir");
                    httpClientService.getConversionTwoCurrency(baseTypeCurrency, targetTypeCurrency, amount);
                    System.out.println("El resultado de la conversion de:\n " + conversion.getAmountEntered() + " " + baseTypeCurrency.name() + " es de " + conversion.getAmountResult() + " " + targetTypeCurrency.name());
                    System.out.println("*** Si desea puede ver sus ultimas conversiones en el historial ***\n");

                    Util.mainMenu();
                    break;
                }
                case 4:{
                    break;
                }
                case 5:{
                    System.out.println("************ Gracias por utilizar nuestro conversor de moneda");
                    exit = true;
                    break;
                }
            }
        }while (!exit);



    }
}