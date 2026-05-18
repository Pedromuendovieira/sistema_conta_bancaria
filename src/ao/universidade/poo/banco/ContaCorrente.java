package ao.universidade.poo.banco;

public class ContaCorrente extends Conta implements Tributavel {

    private double limite;

    public ContaCorrente(String numero, double saldoInicial, double limite) {
        super(numero, saldoInicial);
        if (limite < 0)
            throw new IllegalArgumentException("Limite inválido");
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        if (limite < 0)
            throw new IllegalArgumentException("Limite inválido");
        this.limite = limite;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0)
            throw new IllegalArgumentException("Valor inválido");

        if (getSaldo() + limite < valor) {
            throw new SaldoInsuficienteException("Saldo + limite insuficiente");
        }

        debitar(valor);
    }

    // ===== INTERFACE =====
    @Override
    public void debitarTaxaMensal() {
        double taxa = 10;

        if (getSaldo() + limite < taxa)
            throw new SaldoInsuficienteException("Sem saldo para taxa");

        debitar(taxa);
    }

    @Override
    public String toString() {
        return String.format(
                "ContaCorrente %s - Saldo: %.2f - Limite: %.2f",
                numero, getSaldo(), limite
        );
    }
}