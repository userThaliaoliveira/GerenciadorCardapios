package br.com.gerenciamento;

public class Prato {
    private String nome;
    private float preco;
    private String categoria;

    public Prato(String nome, float preco, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public float getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }
}
