package utilitis;

import enums.TypeCurrency;

public class Util {
    public static TypeCurrency filterCurrencyType(String currencyType){
        currencyType = currencyType.toUpperCase();
        return TypeCurrency.valueOf(currencyType);
    }
    public static void mainMenu(){
        System.out.print("""
                         Ingresa la accion seleccionada:
                         1_ Ver valores de cambio respecto moneda a seleccionar
                         2_ Ver tipos de monedas
                         3_ Realizar una conversion de monedas
                         4_ Ver el historial de conversiones previas
                         5_ Salir
                         """);
    }

}
