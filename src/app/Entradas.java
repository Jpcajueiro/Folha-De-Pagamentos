package app;
import java.util.Scanner;
import java.time.*;
import java.util.InputMismatchException;
import java.time.DateTimeException;

public class Entradas {

    public static boolean ehInt(String n){
        return n != null && n.matches("\\d+");
    }
    public static String lerString (Scanner entrada, String mensagem){
            while(true){
                System.out.println(mensagem);
    
                String n = entrada.nextLine();
                try {
                    if(n.length() > 0){
                        System.out.println();
                        System.out.println("--------------------------------------------\n");
                        return n;
                    }
                } catch(InputMismatchException ex){
                    System.out.println("Ocorreu um erro! Por favor, tente novamente.\n");
                }
            }
    }

    public static int lerInt (Scanner entrada, String mensagem){
        while(true){
            System.out.println(mensagem);
            String n = entrada.nextLine();
            if(ehInt(n)) {
                System.out.println();
                try {
                    System.out.println("--------------------------------------------\n");
                    return Integer.parseInt(n);
                } catch(NumberFormatException ex) {
                    System.out.println("Ocorreu um erro! Por favor, tente novamente, inserindo um número inteiro.\n");
                }
            }
            System.out.println("Ocorreu um erro! Por favor, tente novamente, inserindo um número inteiro.\n");
        }
    }

    public static int lerEntre(Scanner entrada, String mensagem, int inicio, int fim){
        while(true){
            int opt = lerInt(entrada, mensagem);

            if(opt >= inicio && opt <= fim){
                System.out.println();
                return opt;
            }
            System.out.println("Ocorreu um erro! Por favor, tente novamente.\n");
            System.out.println("Por favor, insira um número inteiro entre " + inicio + " e " + fim + "\n");
        } 
    }

    protected static LocalDate lerData(Scanner entrada){
        while(true){
            try {
                int dia = lerEntre(entrada, "Insira o dia", 1, 31);
                int mes = lerEntre(entrada, "Insira o número do mês:", 1, 12);
                int ano = lerInt(entrada, "Insira o ano:");

                LocalDate date = LocalDate.of(ano, mes, dia);
                return date;
            } 
            catch (DateTimeException ex){
                System.out.println("Ocorreu um erro e essa data é inválida! Vamos tentar novamente.");
            }
        }
    }

    protected static LocalTime lerHorario(Scanner entrada, String mensagem){
        while(true) {
            System.out.println(mensagem);
            System.out.println("Formato 24 horas.\n");
            try {
                int hora = lerEntre(entrada, "Insira a hora:", 0, 23);
                int minuto = lerEntre(entrada, "Insira os minutos:", 0, 59);

                LocalTime time = LocalTime.of(hora, minuto);
                return time;
            }
            catch (Exception ex){
                System.out.println("Ocorreu um erro e essa hora é inválida! Vamos tentar novamente.");
            }
        }
    }
}
