package br.com.gerenciamento;

import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    private String cidade;
    private List<Prato> pratos;

    public Cardapio(String cidade) {
        this.cidade = cidade;
        this.pratos = new ArrayList<>();
    }

    public void adicionarPrato(Prato prato) {
        pratos.add(prato);
    }

    public int[] exibirResumoCategoria(String categoria) {
        int quantidade = 0;
        float precoTotal = 0.0f;
        for (Prato prato : pratos) {
            if (prato.getCategoria().equalsIgnoreCase(categoria)) {
                quantidade++;
                precoTotal += prato.getPreco();
            }
        }
        System.out.printf("Unidade %s: Quantidade = %d, Preço = R$ %.2f\n", cidade, quantidade, precoTotal);
        return new int[] { quantidade, (int) precoTotal };
    }

    public void exibirDetalhesCategoria(String categoria) {
        System.out.printf("Unidade %s - Detalhes dos Pratos:\n", cidade);
        for (Prato prato : pratos) {
            if (prato.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.printf("%s -> R$ %.2f\n", prato.getNome(), prato.getPreco());
            }
        }
    }
}
