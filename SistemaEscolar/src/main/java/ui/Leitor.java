package ui;

import java.util.Scanner;

public class Leitor {

    public static int lerInt() {
        Scanner scanner = new Scanner(System.in);
        int valor;

        while (true) {
            try {
                valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e){
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }

    }

    public static int lerInt(String msg) {
        System.out.println(msg);
        return lerInt();
    }

    public static int lerInt(String msg, int min, int max, String msgErro) {
        int valor;
        do {
            valor = lerInt(msg);
            if (valor < min || valor > max ) {
                System.out.println(msgErro);
            }
        } while (valor < min || valor > max);
        return valor;
    }

    public static int lerInt(String msg, int min, int max) {
        int valor;
        do {
            valor = lerInt(msg);
            if (valor < min || valor > max ) {
                System.out.println("Valor Inválido");
            }
        } while (valor < min || valor > max);
        return valor;
    }

    public static int lerInt(String msg, int min) {
        int valor;
        do {
            valor = lerInt(msg);
            if (valor < min) {
                System.out.println("Valor Inválido");
            }
        } while (valor < min);
        return valor;
    }

    public static double lerDouble(String msg, double min){
        double valor;
        do {
            valor = lerDouble(msg);
            if(valor < min){
                System.out.println("Valor inválido.");
            }
        } while (valor < min);
        return valor;
    }

    public static double lerDouble(){
        return new Scanner(System.in).nextDouble();
    }

    public static double lerDouble(String msg){
        System.out.println(msg);
        return lerDouble();
    }

    public static String lerString(){
        return new Scanner(System.in).nextLine();
    }

    public static String lerString(String msg){
        System.out.println(msg);
        return lerString();
    }


}
