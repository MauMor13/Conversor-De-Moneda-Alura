import DTOs.MultiCurrencyConversionDTO;
import DTOs.TypeCurrencyDTO;
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

        //traigo la lista de tipos de cambio
        TypeCurrencyDTO typeCurrencyDTO = httpClientService.getTypeCurrencies();

        //creacion de un menu de usuario
        System.out.println("******************** Conversion de Monedas ********************\n");
        Util.mainMenu();

        //booleano de salida de app
        boolean exit = false;

        do {
            byte numSelection = Util.validationByte((byte) 5, (byte) 1);
            switch (numSelection){
                case 1:{
                    TypeCurrency typeCurrency = Util.controlCurrencyType("**** Ingrese un tipo de moneda el cual quiera ver conversiones respecto a esta ****");
                    httpClientService.getMultipleConversion(typeCurrency);
                    Util.mainMenu();
                    break;
                }
                case 2:{
                    if (typeCurrencyDTO != null){
                        System.out.println("""
                        Seleccione una opción de búsqueda o impresión total:
                        1_ Búsqueda por ingreso de palabra clave
                        2_ impresión de todos los tipos de cambio
                        """);
                        byte select = Util.validationByte((byte) 2, (byte) 1);
                        switch (select){
                            case 1:{
                                Util.filterCurrencyType(typeCurrencyDTO);
                                break;
                            }
                            case 2:{
                                Util.printingCurrencyTypes(typeCurrencyDTO.supported_codes()," Aquí podrá ver los tipos de cambio: \n Código ISO ------ Nombre de Moneda ");
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("A ocurrido un error vuelva a intentarlo");
                    }
                    Util.mainMenu();
                    break;
                }
                case 3:{
                    System.out.println("** Ingrese el monto a convertir:");
                    double amount = Util.entryAmountCheck();
                    TypeCurrency baseTypeCurrency = Util.controlCurrencyType(" ** Ingrese el tipo de cambio de este monto " + amount + "\n ** Ejemplo: USD - ARS - EUR");
                    TypeCurrency targetTypeCurrency = Util.controlCurrencyType("Por ultimo ingrese el tipo de cambio al cual desea convertir");
                    httpClientService.getConversionTwoCurrency(baseTypeCurrency, targetTypeCurrency, amount);
                    Util.mainMenu();
                    break;
                }
                case 4:{
                    System.out.println(" ****** Su historial de conversiones ****** ");

                    Util.mainMenu();
                    break;
                }
                case 5:{
                    System.out.println("************ Gracias por utilizar nuestro conversor de moneda ************");
                    exit = true;
                    break;
                }
            }
        }while (!exit);



    }
}