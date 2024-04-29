import DTOs.TypeCurrencyDTO;
import enums.TypeCurrency;
import models.Conversion;
import services.FileManagementService;
import services.HttpClientService;
import services.impl.FileManagementImpl;
import services.impl.HttpClientImpl;
import utilitis.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        //instancia de servicios
        FileManagementService fileManagementService = new FileManagementImpl();
        HttpClientService httpClientService = new HttpClientImpl();

        //crear una nueva lista de conversiones para el historial
        List<Conversion> listConversionHistory = fileManagementService.readFileHistory();

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
                    TypeCurrency typeCurrency = Util.controlCurrencyType("**** Ingrese un tipo de moneda a la cual quiera ver sus conversiones ****");
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
                    httpClientService.getConversionTwoCurrency(baseTypeCurrency, targetTypeCurrency, amount, listConversionHistory);
                    Util.mainMenu();
                    break;
                }
                case 4:{
                    System.out.println(" ****** Su historial de conversiones ****** ");
                    Util.printingHistoryConversion(listConversionHistory);
                    System.out.println("\n\n************* ¿Desea borrar el historial ? *************");
                    System.out.println("1_ Si \n2_ No");
                    if (Util.validationByte((byte)2,(byte)1) == 1){
                        listConversionHistory.clear();
                    }
                    Util.mainMenu();
                    break;
                }
                case 5:{
                    fileManagementService.writeFileHistory(listConversionHistory);
                    System.out.println("************ Gracias por utilizar nuestro conversor de moneda ************");
                    exit = true;
                    break;
                }
            }
        }while (!exit);



    }
}