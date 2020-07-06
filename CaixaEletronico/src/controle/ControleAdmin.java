package controle;

import arquivo.ArquivoDAO;
import java.io.File;
import java.util.Scanner;
import getset.Cadastro;
import main.Main;
import main.Util;
import static controle.Idioma.*;

public class ControleAdmin {

    private static Scanner ler = new Scanner(System.in);
    private static ArquivoDAO arqdao = new ArquivoDAO();
    private static Main main = new Main();

    public void inicio() {
        System.out.println(msg30);
        System.out.print(msg13);
        int resposta = ler.nextInt();
        if (resposta > 3 || resposta < 1) {
            inicio();
        }
        switch (resposta) {
            case 1:
                desbloquear();
                break;
            case 2:
                cadastrar();
                break;
                case 3:
               new Main().start();
                break;
            default:;

        }
    }

    public void cadastrar() {
        String conta = new Util().gerarConta(), agencia, senha = "";
        while (arqdao.verificarConta(conta) == false) {
            conta = new Util().gerarConta();
        }
        agencia = new Util().gerarAgencia();
        System.out.println(msg28 + conta);
        System.out.println(msg29 + agencia);

        while (senha.length() != 6 || arqdao.verificarNumeros(senha) == false) {
            System.out.print(msg5);
            senha = ler.next();
        }
        Cadastro cad = new Cadastro();
        cad.setAgencia(agencia);
        cad.setConta(conta);
        cad.setSaldo(0);
        cad.setSenha(senha);
        arqdao.criarConta(cad);
        arqdao.criarExtrato(conta, msg8);
        System.out.println(msg31);
        Util.OK();
        inicio();
    }

    public void desbloquear() {

        String resposta = "";
        if (listarContasAdmin() > 0) {
            System.out.println(msg27);
            resposta = ler.next();
            if (new ArquivoDAO().verificarConta(resposta) == false) {
                ArquivoDAO arqdao = new ArquivoDAO();
                Cadastro cad = new Cadastro();
                cad.setAgencia(arqdao.returnAgencia(resposta));
                cad.setConta(arqdao.returnConta(resposta));
                cad.setSaldo(Double.parseDouble(arqdao.returnSaldo(resposta)));
                cad.setSenha(arqdao.returnSenha(resposta));
                arqdao.criarConta(cad);
                System.out.println(msg32);
                Util.OK();
                inicio();
            } else {

                System.out.println(msg3.replaceAll(", contate o gerente", "").replaceAll(", contact the manager", ""));

            }

        } else {

            System.out.println(msg33);
            Util.OK();
            inicio();
        }

    }

    public int listarContasAdmin() {

        int i;
        int j = 0;
        String contas = "";
        File dir = new File("C:/Banco/");
        if (dir.isDirectory()) {
            String[] lista = dir.list();
            for (i = 0; i < lista.length; i++) {
                if (lista[i].substring(lista[i].length() - 4).equalsIgnoreCase(".txt")) {
                    String nome = lista[i].substring(0, lista[i].length() - 4);
                    if (new ArquivoDAO().bloqueado(nome)) {
                        contas = contas + "\n" + nome;
                        j++;
                    }

                }
            }
        }
        if (j != 0) {
            System.out.println(msg34);
            System.out.println(contas);
        }
        return (j);
    }

}
