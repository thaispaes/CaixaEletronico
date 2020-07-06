package controle;

import arquivo.ArquivoDAO;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import getset.Cadastro;
import getset.ContaStatico;
import main.Main;
import main.Util;
import static controle.Idioma.*;

public class ControleUser {

    private ContaStatico user = new ContaStatico();
    private Scanner ler = new Scanner(System.in);

    public void inicio() {
        System.out.println(msg1+msg35+user.getSaldo());
        System.out.println(msg12);
        System.out.print(msg13);
        int resposta = ler.nextInt();
        if (resposta > 5 || resposta < 1) {
            inicio();
        }
        switch (resposta) {
            case 1:
                saque();
                break;
            case 2:
                extrato(user.getConta());
                break;
            case 3:
                transferencia();
                break;
            case 4:
                deposito();
                break;
            case 5:
                new ContaStatico().destroy();
                new Main().start();
                break;
            default:
                break;

        }
    }

    public void deposito() {
        System.out.println(msg14);
        System.out.print(msg13);
        String valor = ler.next();
        while (new Util().verificarDouble(valor) == false) {
            System.out.println(msg14);
            System.out.print(msg13);
            valor = ler.next();
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:/Banco/" + user.getConta() + ".txt"));
        } catch (FileNotFoundException | StackOverflowError | NullPointerException ex) {

        }
        double saldo = user.getSaldo();
        saldo = saldo + Double.parseDouble(valor);
        Cadastro cad = new Cadastro();
        cad.setAgencia(user.getAgencia());
        cad.setConta(user.getConta());
        cad.setSaldo(saldo);
        cad.setSenha(user.getSenha());
        ArquivoDAO aqdao = new ArquivoDAO();
        aqdao.criarSobrescrever(cad);
        Util.OK();
        try {
            br.close();
        } catch (IOException ex) {

        }
        user.setSaldo(Double.parseDouble(new ArquivoDAO().returnSaldo(user.getConta())));
        inicio();

    }

    public void transferencia() {
        if (new ArquivoDAO().listarContas() > 2) {
            System.out.println(msg15);
            String resposta = ler.next();
            if (new ArquivoDAO().verificarConta(resposta) == false) {
                System.out.println(msg16);
                System.out.print(msg13);
                String valor = ler.next();
                while (new Util().verificarDouble(valor) == false) {
                    System.out.println(msg16);
                    System.out.print(msg13);
                    valor = ler.next();
                }
                if (user.getSaldo() >= Double.parseDouble(valor)) {
                    BufferedReader br = null;
                    try {
                        br = new BufferedReader(new FileReader("C:/Banco/" + resposta + ".txt"));
                    } catch (FileNotFoundException | StackOverflowError | NullPointerException ex) {

                    }
                    try {
                       
                        ArquivoDAO envio = new ArquivoDAO();
                        double saldo = Double.parseDouble(envio.returnSaldo(resposta));
                        saldo = saldo + Double.parseDouble(valor);
                        Cadastro cad = new Cadastro();
                        cad.setAgencia(envio.returnAgencia(resposta));
                        cad.setConta(envio.returnConta(resposta));
                        cad.setSaldo(saldo);
                        cad.setSenha(envio.returnSenha(resposta));
                        ArquivoDAO aqdao = new ArquivoDAO();
                        aqdao.criarSobrescrever(cad);
                        br.close();
                        user.setSaldo(Double.parseDouble(new ArquivoDAO().returnSaldo(user.getConta())));
                        new ArquivoDAO().criarExtrato(user.getConta(), msg17 + valor + " R$ "+msg18);
                        new ArquivoDAO().criarExtrato(resposta, valor + " R$ recebido de " + user.getConta());
                        System.out.println(msg19);
                        retirar(Double.parseDouble(valor));
                        Util.OK();
                        inicio();
                    } catch (IOException | StackOverflowError | NullPointerException ex) {

                    }
                } else {
                    System.out.println(msg20);
                    Util.OK();
                    inicio();
                }

            } else {
                System.out.println(msg21);
                Util.OK();
                inicio();
            }
        } else {
            Util.OK();
            inicio();
        }
    }

    public void saque() {
        System.out.println(msg22+msg35+user.getSaldo());
        System.out.print(msg13);
        String valor = ler.next();
        if (new Util().verificarDouble(valor) == false) {
            saque();
        }
        if (user.getSaldo() >= Double.parseDouble(valor)) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("C:/Banco/" + user.getConta() + ".txt"));
            } catch (FileNotFoundException | StackOverflowError | NullPointerException ex) {

            }
            try {
                double saldo = user.getSaldo();
                saldo = saldo - Double.parseDouble(valor);
                Cadastro cad = new Cadastro();
                cad.setAgencia(user.getAgencia());
                cad.setConta(user.getConta());
                cad.setSaldo(saldo);
                cad.setSenha(user.getSenha());
                ArquivoDAO aqdao = new ArquivoDAO();
                aqdao.criarSobrescrever(cad);
                br.close();
                user.setSaldo(Double.parseDouble(new ArquivoDAO().returnSaldo(user.getConta())));
                new ArquivoDAO().criarExtrato(user.getConta(), msg24 + valor + " R$ "+msg23);
                System.out.println(msg25);
                Util.OK();
                inicio();
            } catch (IOException | StackOverflowError | NullPointerException ex) {

            }
        } else {
            System.out.println(msg20);
            inicio();
        }
    }

    public void retirar(double retirar) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:/Banco/" + user.getConta() + ".txt"));
        } catch (FileNotFoundException | StackOverflowError | NullPointerException ex) {

        }
        double saldo = user.getSaldo();
        saldo = saldo - retirar;
        Cadastro cad = new Cadastro();
        cad.setAgencia(user.getAgencia());
        cad.setConta(user.getConta());
        cad.setSaldo(saldo);
        cad.setSenha(user.getSenha());
        ArquivoDAO aqdao = new ArquivoDAO();
        aqdao.criarSobrescrever(cad);
        try {
            br.close();
        } catch (IOException ex) {

        }
        user.setSaldo(Double.parseDouble(new ArquivoDAO().returnSaldo(user.getConta())));
        inicio();
    }

    public void extrato(String conta) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:/Banco/Extrato/" + conta + "extrato.txt"));
        } catch (FileNotFoundException | StackOverflowError | NullPointerException ex) {

        }

        try {
            while (br.ready()) {
                String linha = br.readLine();
                System.out.println(linha);
            }
            System.out.println(msg35+user.getSaldo());
            Util.OK();
            br.close();
            inicio();
        } catch (IOException | StackOverflowError | NullPointerException ex) {

        }

    }
}
