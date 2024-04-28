package utilitis;
import DTOs.TypeCurrencyDTO;
import enums.TypeCurrency;
import java.util.*;
import java.util.stream.Collectors;

public class Util {

    private static final Scanner scn = new Scanner(System.in);

    //impresion de menu reutilizable
    public static void mainMenu(){
        System.out.print("""
                         ****************************************************************
                         *             Ingresa la opción a realizar:                    *
                         * 1_ Ver valores de cambio respecto moneda a seleccionar       *
                         * 2_ Buscar tipo de cambio por código de moneda                *
                         * 3_ Realizar una conversion de monedas                        *
                         * 4_ Ver el historial de conversiones previas                  *
                         * 5_ Salir                                                     *
                         ****************************************************************
                         """);
    }

    //manejo de error: retorno de booleano
    public static boolean isCurrencyValid(String currencyName) {
        try {
            TypeCurrency.valueOf(currencyName);
            return true; // El valor está presente en el enum
        } catch (IllegalArgumentException e) {
            return false; // El valor no está presente en el enum
        }
    }

    //control de string cambio a mayuscula retorna enum correspondiente
    public static TypeCurrency controlCurrencyType(String message) {
        System.out.println(message);
        String currencyType;
        boolean valControl = true;
        do {
            while (!scn.hasNext()){
                System.out.println("! El valor ingresado es incorrecto vuelva a intentarlo ¡");
                scn.next();
            }
            currencyType = scn.next().toUpperCase();
            valControl = isCurrencyValid(currencyType);
            if (!valControl){
                System.out.println("*** EL valor ingresado no se encuentra presente en el registro de monedas ***");
            }
        }while (!valControl);

        return TypeCurrency.valueOf(currencyType);
    }

    //control de ingreso de monto correcto
    public static Double entryAmountCheck(){
        double entryDate;
        boolean valControl;
        do {
            while (!scn.hasNextDouble()){
                System.out.println("! El valor ingresado es incorrecto vuelva a intentarlo ¡");
                scn.next(); //consumo la entrada no valida
            }
            entryDate = scn.nextDouble();
            valControl = entryDate > 0;
            if(!valControl) {
                System.out.println("*******¡ El valor debe ser mayor a cero !*******");
            }
        }while (!valControl);
        return entryDate;
    }

    //control de ingreso de selección válida con byte
    public static byte validationByte(byte max, byte min){
        byte numSelect;
        boolean valControl;
        do {
            while (!scn.hasNextByte()){
                System.out.println("! El valor ingresado es incorrecto vuelva a intentarlo ¡");
                scn.next();
            }
            numSelect = scn.nextByte();
            valControl = numSelect >= min && numSelect <= max;
            if (!valControl){
                System.out.println("El valor de opción tiene que ser entre " + min + " y " + max);
            }
        }while (!valControl);
        return numSelect;
    }

    //validación de un string ingresado
    public static String validationString(){
        String entryString;
        boolean valControl = true;
        do {
            while (!scn.hasNext()){
                System.out.println("! El valor ingresado es incorrecto vuelva a intentarlo ¡");
                scn.next();
            }
            entryString = scn.next();
            if (!entryString.isEmpty()){
                valControl = false;
            }
        }while (valControl);
        return entryString;
    }

    //Impresión de tipos de cambio por grupo de 15 elementos
    public static void printingCurrencyTypes(HashMap<?, ?> listElement, String message){
        System.out.println(message);
        int count = 0;
        int groupSize = 15;
            for (Map.Entry<?, ?> entry : listElement.entrySet()){
                System.out.println("        " + entry.getKey() + " ------ " + entry.getValue());
                count++;
                if (count % groupSize == 0){
                    System.out.println("""
                    ********* ¿ Desea seguir viendo los tipos de cambio ? *********
                    Ingrese :
                    1_ Continuar
                    2_ Salir
                    """);
                    if (validationByte((byte)2, (byte) 1) == 2){
                        break;
                    }
                }
            }
    }

    //filtro de búsqueda de tipo de cambio por ingreso de palabra clave
    public static void filterCurrencyType(TypeCurrencyDTO typeCurrencyDTO){
        Map<String, String> listCode = typeCurrencyDTO.supported_codes();
        boolean exit = true;
        String search;
        do {
            System.out.println("** Ingrese letra o parte del nombre de tipo de cambio buscado");
            search = validationString();
            String finalSearch = search;
            Map<String, String> resultado = listCode.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().contains(finalSearch))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            // Imprimir el resultado
            System.out.println("Resultados filtrados:");
            if (resultado.isEmpty()){
                System.out.println("*** No se encontraron coincidencias ***");
            }
            else {
                resultado.forEach((clave, valor) -> System.out.println(clave + " -> " + valor));
            }
            System.out.println("""
                    ********* ¿ Desea realizar otra búsqueda ? *********
                    Ingrese :
                    1_ Continuar
                    2_ Salir
                    """);
            exit = !(validationByte((byte)2, (byte) 1) == 2);
        }while (exit);
    }

}
