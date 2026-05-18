package ao.universidade.poo.banco;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;
    private List<Conta> contas = new ArrayList<>();

    public Cliente(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome inválido");
        this.nome = nome;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public List<Conta> getContas() {
        return contas;
    }

    public String getNome() {
        return nome;
    }

    public void transferirEntreContas(Conta origem, Conta destino, double valor) {
        origem.transferir(destino, valor);
    }
}
