package ao.universidade.poo.banco;

public class Conta {

    protected String numero;
    private double saldo;

    public Conta(String numero, double saldoInicial) {
        if (numero == null || numero.isBlank())
            throw new IllegalArgumentException("Número inválido");
        this.numero = numero;
        this.saldo = saldoInicial;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    // ===== NOVO ENCAPSULAMENTO =====
    protected void creditar(double valor) {
        saldo += valor;
    }

    protected void debitar(double valor) {
        saldo -= valor;
    }

    // ===== OPERAÇÕES =====
    public void depositar(double valor) {
        if (valor <= 0)
            throw new IllegalArgumentException("Valor inválido");
        creditar(valor);
    }

    public void sacar(double valor) {
        if (valor <= 0)
            throw new IllegalArgumentException("Valor inválido");

        if (saldo < valor)
            throw new SaldoInsuficienteException("Saldo insuficiente");

        debitar(valor);
    }

    public void transferir(Conta destino, double valor) {
        if (destino == null)
            throw new IllegalArgumentException("Destino inválido");

        this.sacar(valor);
        destino.depositar(valor);
    }

    @Override
    public String toString() {
        return String.format("Conta %s - Saldo: %.2f", numero, saldo);
    }
}