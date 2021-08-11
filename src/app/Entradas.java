package app;
import java.util.Scanner;
import java.time.*;

public class Entradas {
    public static String lerString (Scanner entrada){
        String string = entrada.nextLine();
        System.out.println("--------------------------------------------");
        return string;
    }
    public static int lerInt (Scanner entrada){
        int i = entrada.nextInt();
        entrada.nextLine();
        System.out.println("--------------------------------------------");
        return i;
    }

    public static Double lerDouble(Scanner entrada){
        double d = entrada.nextDouble();
        entrada.nextLine();
        System.out.println("--------------------------------------------");
        return d;
    }

    public static LocalDate lerData(Scanner entrada) {
        System.out.println("Dia: ");
        int dia = entrada.nextInt();
        System.out.println("Mês: ");
        int mes = entrada.nextInt();
        System.out.println("Ano: ");
        int ano = entrada.nextInt();
        return LocalDate.of(ano, mes, dia);
    }
}