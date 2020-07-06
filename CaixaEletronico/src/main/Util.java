package main;

import java.util.Scanner;
import static controle.Idioma.msg9;

public class Util {

    public String gerarConta() {
        return String.valueOf(10000000 + (int) (Math.random() * (99999999 - 10000000)));
    }

    public String gerarAgencia() {
        return String.valueOf(1000 + (int) (Math.random() * (9999 - 1000)));
    }

    public boolean verificarDouble(String valor) {
        boolean numeros;
        try {
            Double.valueOf(valor);
            numeros = true;
        } catch (NumberFormatException e) {
            numeros = false;
        }
        return numeros;
    }

    public static void OK() {
        Scanner ok = new Scanner(System.in);
        System.out.print(msg9);
        String oks = ok.nextLine();
    }
}
