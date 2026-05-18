package ao.universidade.poo.banco;

import java.util.ArrayList;
import java.util.List;

public class BancoApp {
    public static void main(String[] args) {

        List<Conta> contas = new ArrayList<>();

        ContaCorrente cc1 = new ContaCorrente("001", 500.0, 300.0);
        ContaPoupanca cp1 = new ContaPoupanca("002", 1000.0, 0.01);

        contas.add(cc1);
        contas.add(cp1);

        System.out.println("=== Estado inicial das contas ===");
        imprimirContas(contas);

        // POLIMORFISMO
        System.out.println("\n=== Depositando 200 em todas as contas ===");
        for (Conta c : contas) {
            c.depositar(200);
        }
        imprimirContas(contas);

        // SAQUE CONTA CORRENTE
        System.out.println("\n=== Saque na conta corrente ===");
        try {
            cc1.sacar(1000); // saldo + limite permite
            System.out.println("Saque efetuado.");
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        imprimirContas(contas);

        // SAQUE POUPANÇA (ERRO INTENCIONAL)
        System.out.println("\n=== Saque na poupança (erro esperado) ===");
        try {
            cp1.sacar(5000);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        imprimirContas(contas);

        // TRANSFERÊNCIA
        System.out.println("\n=== Transferência poupança -> corrente ===");
        try {
            cp1.transferir(cc1, 300);
            System.out.println("Transferência efetuada.");
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        imprimirContas(contas);

        // RENDIMENTO (SEM instanceof ContaPoupanca - MELHOR PRÁTICA)
        System.out.println("\n=== Aplicar rendimento (polimorfismo limpo) ===");
        for (Conta c : contas) {
            if (c instanceof ContaPoupanca cp) {
                cp.aplicarRendimento();
                System.out.println("Rendimento aplicado em " + cp.getNumero());
            }
        }

        imprimirContas(contas);

        // TAXA (INTERFACE TRIBUTAVEL - se implementaste)
        System.out.println("\n=== Taxa mensal (interface Tributavel) ===");
        for (Conta c : contas) {
            if (c instanceof Tributavel t) {
                t.debitarTaxaMensal();
                System.out.println("Taxa debitada em " + c.getNumero());
            }
        }

        imprimirContas(contas);
    }

    private static void imprimirContas(List<Conta> contas) {
        System.out.println("\n--- Estado das contas ---");
        for (Conta c : contas) {
            System.out.println(c);
        }
    }
}