package utilitis;

import enums.TypeCurrency;

import java.util.Scanner;

public class Util {

    private static final Scanner scn = new Scanner(System.in);

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

    //impresion de menu reutilizable
    public static void mainMenu(){
        System.out.print("""
                         ****************************************************************
                         *             Ingresa la opción a realizar:                    *
                         * 1_ Ver valores de cambio respecto moneda a seleccionar       *
                         * 2_ Buscar tipo de cambio por nombre de moneda                *
                         * 3_ Realizar una conversion de monedas                        *
                         * 4_ Ver el historial de conversiones previas                  *
                         * 5_ Salir                                                     *
                         ****************************************************************
                         """);
    }

    //metodo de control de ingreso de monto correcto
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

    public static byte validationByte(){
        byte numSelect;
        boolean valControl;
        do {
            while (!scn.hasNextByte()){
                System.out.println("! El valor ingresado es incorrecto vuelva a intentarlo ¡");
                scn.next();
            }
            numSelect = scn.nextByte();
            valControl = numSelect > 0 && numSelect <= 5;
            if (!valControl){
                System.out.println("El valor de opción tiene que ser entre 1 y 5");
            }
        }while (!valControl);
        return numSelect;
    }

}
