package main;

import getset.ContaStatico;
import controle.Idioma;
import controle.ControleUser;
import controle.ControleAdmin;
import arquivo.ArquivoDAO;
import java.util.Scanner;
import static controle.Idioma.*;

public class Main {

    private static Scanner ler = new Scanner(System.in);
    private static ArquivoDAO arqdao = new ArquivoDAO();
    private static Main main = new Main();

    public static void main(String[] args) {
        int resposta = 0;
        while (resposta != 1 && resposta != 2) {
            System.out.println("Escolha seu idioma/Choose your language");
            System.out.println("1-Português/Portuguese");
            System.out.println("2-Inglês/English");
            System.out.print("R/A: ");
            String valor = ler.next();
            try {
                resposta = Integer.parseInt(valor);
            } catch (NumberFormatException ex) {
                resposta = 0;
            }

        }
        if (resposta == 1) {
            Idioma.portugues();
        } else {
            Idioma.ingles();
        }
        new Main().start();
    }

    public void start() {
        arqdao.criar();
        System.out.print("1-Modo Admin/Admin Mode\n2-Modo Usuário/User Mode: ");
        String user = ler.next();
        while (!user.equals("1") && !user.equals("2")) {
            System.out.print("1-Modo Admin/Admin Mode\n2-Modo Usuário/User Mode: ");
            user = ler.next();

        }
        if (user.equals("2")) {
            main.login();
        } else {
            int erro = 0;
            String senha = "";

            while (erro != 3) {
                System.out.print("Senha/Password: ");
               senha = ler.next();
                if (senha.equals("1234")) {
                    main.admin();
                } else {
                    erro++;
                }

            }
            start();
        }
    }

    public void login() {
        System.out.println(msg1);
        String conta = "";
        while (conta.length() < 8 || conta.length() > 8 || arqdao.verificarNumeros(conta) == false) {
            System.out.print(msg2);
            conta = ler.next();

        }
        if (arqdao.verificarConta(conta)) {
            System.out.println(msg3);
        } else {
            String agencia = "", senha = "";
            while (agencia.length() < 4 || agencia.length() > 4 || arqdao.verificarNumeros(agencia) == false) {
                System.out.print(msg4);
                agencia = ler.next();

            }
            int erro = 0;
            while (senha.length() < 6 || senha.length() > 6 || arqdao.verificarNumeros(senha) == false) {
                System.out.print(msg5);
                senha = ler.next();
                erro++;
                if (erro == 4) {
                    System.out.println(msg6);
                    arqdao.bloquearConta(conta);
                    main.login();
                }
            }
            if (agencia.equals(arqdao.returnAgencia(conta)) && senha.equals(arqdao.returnSenha(conta))) {
                if (arqdao.bloqueado(conta)) {
                    System.out.println(msg6);
                    main.login();
                } else {
                    ContaStatico ctstat = new ContaStatico();
                    ControleUser user = new ControleUser();
                    ctstat.setAgencia(arqdao.returnAgencia(conta));
                    ctstat.setConta(arqdao.returnConta(conta));
                    ctstat.setSaldo(Double.parseDouble(arqdao.returnSaldo(conta)));
                    ctstat.setSenha(arqdao.returnSenha(conta));
                    arqdao.criarExtrato(conta, msg8);
                    user.inicio();

                }

            } else {
                System.out.println(msg7);
                main.login();
            }
        }
    }

    public void admin() {
        System.out.println(msg1 + " Admin");
        new ControleAdmin().inicio();
    }

}
