package arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import getset.Cadastro;
import getset.ContaStatico;
import static controle.Idioma.*;

public class ArquivoDAO {

    public void criar() {
        File file;
        String caminho = "/Banco";
        file = new File(caminho);
        if (!file.exists()) {
            file.mkdirs();
        }
        caminho = "C:/Banco/Extrato";
        file = new File(caminho);
        if (!file.exists()) {
            file.mkdirs();
        }

    }

    public void criarArquivo(String conta) {
        File file;
        String caminho = "C:/Banco/" + conta + ".txt";
        file = new File(caminho);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {

            }
        }
    }

    public boolean verificarConta(String conta) {
        File file;
        boolean existe = false;
        String caminho = "C:/Banco/" + conta + ".txt";
        file = new File(caminho);
        if (!file.exists()) {
            existe = true;
        }
        return existe;
    }

    public void criarConta(Cadastro cad) {
        criarArquivo(String.valueOf(cad.getConta()));
        FileWriter arq = null;
        try {
            arq = new FileWriter("C:/Banco/" + cad.getConta() + ".txt");
        } catch (IOException | StackOverflowError | NullPointerException ex) {

        }
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("conta=" + cad.getConta() + "%n");
        gravarArq.printf("senha=" + cad.getSenha() + "%n");
        gravarArq.printf("saldo=" + cad.getSaldo() + "%n");
        gravarArq.printf("agencia=" + cad.getAgencia() + "%n");

        try {
            arq.close();
        } catch (IOException | StackOverflowError | NullPointerException ex) {

        }
    }

    public void criarSobrescrever(Cadastro cad) {
        FileWriter arq = null;
        try {
            arq = new FileWriter("C:/Banco/" + cad.getConta() + ".txt");
        } catch (IOException | StackOverflowError | NullPointerException ex) {
            System.out.println("erro"+ex);
        }
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("conta=" + cad.getConta() + "%n");
        gravarArq.printf("senha=" + cad.getSenha() + "%n");
        gravarArq.printf("saldo=" + cad.getSaldo() + "%n");
        gravarArq.printf("agencia=" + cad.getAgencia() + "%n");
        try {
            arq.close();
        } catch (IOException | StackOverflowError | NullPointerException ex) {
            System.out.println("erro" + ex);
        }
    }

    public void bloquearConta(String conta) {

        FileWriter arq = null;
        try {
            arq = new FileWriter("C:/Banco/" + conta + ".txt", true);
        } catch (IOException | StackOverflowError | NullPointerException ex) {

        }
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("bloqueado" + "%n");

        try {
            arq.close();
        } catch (IOException | StackOverflowError | NullPointerException ex) {

        }
    }

    public boolean bloqueado(String conta) {
        boolean bloqueado = false;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:/Banco/" + conta + ".txt"));
        } catch (FileNotFoundException | StackOverflowError | NullPointerException ex) {

        }
        try {
            int i = 0;
            while (br.ready()) {
                String linha = br.readLine();
                if (linha.equals("bloqueado")) {
                    bloqueado = true;
                }

            }
            br.close();
        } catch (IOException | StackOverflowError | NullPointerException ex) {

        }
        return bloqueado;
    }

    public String returnConta(String conta2) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:/Banco/" + conta2 + ".txt"));
        } catch (FileNotFoundException | StackOverflowError | NullPointerException ex) {

        }
        String conta = null;
        try {
            int i = 0;
            while (br.ready()) {
                String linha = br.readLine();
                if (i == 0) {
                    conta = linha.replaceAll("conta=", "");
                }
                i += 1;
            }
            br.close();
        } catch (IOException | StackOverflowError | NullPointerException ex) {

        }
        return conta;
    }

    public String returnSenha(String conta) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:/Banco/" + conta + ".txt"));
        } catch (FileNotFoundException | StackOverflowError | NullPointerException ex) {

        }
        String senha = null;
        try {
            int i = 0;
            while (br.ready()) {
                String linha = br.readLine();
                if (i == 1) {
                    senha = linha.replaceAll("senha=", "");
                }
                i += 1;
            }
            br.close();
        } catch (IOException | StackOverflowError | NullPointerException ex) {

        }
        return senha;
    }

    public String returnSaldo(String conta) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:/Banco/" + conta + ".txt"));
        } catch (FileNotFoundException | StackOverflowError | NullPointerException ex) {

        }
        String saldo = null;
        try {
            int i = 0;
            while (br.ready()) {
                String linha = br.readLine();
                if (i == 2) {
                    saldo = linha.replaceAll("saldo=", "");
                }
                i += 1;
            }
            br.close();
        } catch (IOException | StackOverflowError | NullPointerException ex) {

        }
        return saldo;
    }

    public String returnAgencia(String conta) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:/Banco/" + conta + ".txt"));
        } catch (FileNotFoundException | StackOverflowError | NullPointerException ex) {

        }
        String saldo = null;
        try {
            int i = 0;
            while (br.ready()) {
                String linha = br.readLine();
                if (i == 3) {
                    saldo = linha.replaceAll("agencia=", "");
                }
                i += 1;
            }
            br.close();
        } catch (IOException | StackOverflowError | NullPointerException ex) {

        }
        return saldo;
    }

    public boolean verificarNumeros(String conta) {
        boolean numeros;
        try {
            Integer.valueOf(conta);
            numeros = true;
        } catch (NumberFormatException e) {
            numeros = false;
        }
        return numeros;
    }

    public int listarContas() {

        int i = 0;
        String contas = "";
        File dir = new File("C:/Banco/");
        String minhaconta = new ContaStatico().getConta();
        if (dir.isDirectory()) {
            String[] lista = dir.list();
            for (i = 0; i < lista.length; i++) {
                if (lista[i].substring(lista[i].length() - 4).equalsIgnoreCase(".txt")) {

                    String nome = lista[i].substring(0, lista[i].length() - 4);

                    if (!nome.equals(minhaconta)) {
                        contas = contas + nome;
                    }

                }
            }
        }
        if (i > 2) {
            System.out.println(msg10);
            System.out.println(contas);
        } else {
            System.out.println(msg11);
        }

        return (i);
    }

    public void criarExtrato(String conta, String info) {

        FileWriter arq = null;
        try {
            arq = new FileWriter("C:/Banco/Extrato/" + conta + "extrato.txt", true);
        } catch (IOException | StackOverflowError | NullPointerException ex) {

        }
        Calendar cal = Calendar.getInstance();

        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("%n" + info + " - " + cal.getTime() + "%n");

        try {
            arq.close();
        } catch (IOException | StackOverflowError | NullPointerException ex) {

        }
    }

}
