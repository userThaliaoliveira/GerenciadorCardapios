package br.com.cardapio;

import java.util.ArrayList;
import java.util.List;

import Prato.Prato;

public class Cardapio {
    private List<Prato> pratos;
    private String cidade;

    public Cardapio(String cidade) {
        this.cidade = cidade;
        pratos = new ArrayList<>();
    }

    public void adicionarPrato(Prato prato) {
        pratos.add(prato);
    }

    public void exibirPratos() {
        for (Prato prato : pratos) {
            prato.exibirInformacoes();
        }
    }

    public void exibirDetalhesCategoria(String categoria) {
        int quantidade = 0;
        float precoTotal = 0;
        for (Prato prato : pratos) {
            if (prato.getCategoria().equalsIgnoreCase(categoria)) {
                quantidade++;
                precoTotal += prato.getPreco();
            }
        }
        System.out.println("Unidade " + cidade + ": Quantidade = " + quantidade + ", Preço = R$ " + String.format("%.2f", precoTotal));
    }
}
