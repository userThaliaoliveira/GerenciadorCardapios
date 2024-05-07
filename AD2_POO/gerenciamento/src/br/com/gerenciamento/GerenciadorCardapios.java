package br.com.gerenciamento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorCardapios {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Cardapio> cardapios = new ArrayList<>();

        System.out.println("Seja bem-vindo(a)!");
        System.out.print("Quantas unidades serão cadastradas? ");
        int unidades = Integer.parseInt(scanner.nextLine());

        // Carregar dados de todas as unidades a partir dos arquivos fornecidos
        for (int i = 0; i < unidades; i++) {
            System.out.print("Arquivo da Unidade " + (i + 1) + ": ");
            String arquivo = scanner.nextLine();
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String cidade = br.readLine().trim().split(" ")[1];
                Cardapio cardapio = new Cardapio(cidade);
                String line = br.readLine();
                while (line != null) {
                    String[] partes = line.split(";");
                    if (partes.length == 3) {
                        Prato prato = new Prato(partes[0].trim(), Float.parseFloat(partes[1].trim()), partes[2].trim());
                        cardapio.adicionarPrato(prato);
                    }
                    line = br.readLine();
                }
                cardapios.add(cardapio);
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + arquivo);
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Erro ao processar os dados do arquivo: " + arquivo);
                e.printStackTrace();
            }
        }

        // Loop para processar a categoria escolhida e exibir o total
        String categoria;
        while (true) {
            System.out.print("Escolha uma categoria, ou digite '0' para encerrar: ");
            categoria = scanner.nextLine();
            if (categoria.equals("0")) break;

            int totalQuantidade = 0;
            float totalPreco = 0.0f;

            // Exibir a quantidade e o preço total para cada unidade (exceto Prato Principal)
            if (!categoria.equalsIgnoreCase("prato principal")) {
                for (Cardapio cardapio : cardapios) {
                    int[] resumo = cardapio.exibirResumoCategoria(categoria);
                    totalQuantidade += resumo[0];
                    totalPreco += resumo[1];
                }

                // Exibir o total acumulado de todas as unidades (exceto Prato Principal)
                System.out.printf("Total: Quantidade = %d, Preço = R$ %.2f\n", totalQuantidade, totalPreco);
            }

            // Perguntar ao usuário se deseja ver os detalhes de Prato Principal ou outras categorias
            System.out.print("Exibir Detalhes (s/n)? ");
            String opcaoDetalhes = scanner.nextLine();
            if (opcaoDetalhes.equalsIgnoreCase("s")) {
                for (Cardapio cardapio : cardapios) {
                    cardapio.exibirDetalhesCategoria(categoria);
                }
            }
        }

        scanner.close();
    }
}
