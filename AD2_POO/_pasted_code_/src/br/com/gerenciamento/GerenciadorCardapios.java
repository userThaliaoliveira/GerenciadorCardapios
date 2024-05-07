package br.com.gerenciamento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.cardapio.Cardapio;

public class GerenciadorCardapios {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Cardapio> cardapios = new ArrayList<>();

        System.out.println("Seja bem-vindo(a)!");
        System.out.print("Quantas unidades serão cadastradas? ");
        int unidades = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < unidades; i++) {
            System.out.print("Arquivo da Unidade " + (i + 1) + ": ");
            String arquivo = scanner.nextLine();
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String cidade = br.readLine().trim().split(" ")[1]; // Ajuste no trim para garantir que espaços extras não sejam um problema
                Cardapio cardapio = new Cardapio(cidade);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] partes = line.split(";");
                    if (partes.length == 3) { // Garante que a linha está no formato esperado
                        Prato prato = new Prato(partes[0].trim(), Float.parseFloat(partes[1].trim()), partes[2].trim());
                        cardapio.adicionarPrato(prato);
                    }
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

        String categoria;
        while (true) {
            System.out.print("Escolha uma categoria, ou digite “0” para encerrar: ");
            categoria = scanner.nextLine();
            if (categoria.equals("0")) break;
            for (Cardapio cardapio : cardapios) {
                cardapio.exibirDetalhesCategoria(categoria);
            }
        }

        scanner.close();
    }
}
