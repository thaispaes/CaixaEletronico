package getset;

public class ContaStatico {

    private static String senha, agencia, conta;
    private static double saldo;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        ContaStatico.senha = senha;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        ContaStatico.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        ContaStatico.conta = conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void destroy() {
        setAgencia("");
        setConta("");
        setSenha("");
        setSaldo(0);

    }
}
