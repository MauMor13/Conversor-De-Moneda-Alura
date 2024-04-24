import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //creacion de un menu para el usuario y de version de preuba
        System.out.println("******************** Conversion de Monedas ********************");
        Scanner scn = new Scanner(System.in);
        System.out.print("""
                         Ingresa la accion seleccionada:
                         1_ Ver todas las monedas respecto a moneda a seleccionar
                         2_ Ver tipos de monedas
                         3_ Realizar una conversion de monedas
                         4_ Ver el historial de conversiones previas
                         5_ Salir
                         """);
        byte numSelection = scn.nextByte();


    }
}