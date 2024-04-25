import enums.TypeCurrency;
import models.Conversion;
import utilitis.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //instancia del historial de conversiones
        List<Conversion> listConversionHistory = new ArrayList<>();
        //var de tipo de modena local
        TypeCurrency localTypeCurrency;

        //creacion de un menu para el usuario y de version de preuba
        System.out.println("******************** Conversion de Monedas ********************");
        Scanner scn = new Scanner(System.in);
        Util.mainMenu();
        byte numSelection = scn.nextByte();
        switch (numSelection){
            case 1:{

                break;
            }
            case 2:{

                break;
            }
            case 3:{
                System.out.println("Ingrese el monto a convertir:");
                double amount = scn.nextDouble();
                System.out.println("Ingrese el tipo de cambio de este monto " + amount + "Ejemplo: USD o ARG");
                String prevType = scn.next();
                TypeCurrency baseTypeCurrency = Util.filterCurrencyType(prevType);
                System.out.println("Por ultimo ingrese el tipo de cambio al cual desea convertir");
                String postType = scn.next();
                TypeCurrency targetTypeCurrency = Util.filterCurrencyType(postType);
                Conversion conversion = new Conversion(baseTypeCurrency, targetTypeCurrency, amount);
                listConversionHistory.add(conversion);

                break;
            }
            case 4:{
                break;
            }
            case 5:{
                System.out.println("************ Gracias por utilizar nuestro conversor de moneda");
                break;
            }
        }


    }
}