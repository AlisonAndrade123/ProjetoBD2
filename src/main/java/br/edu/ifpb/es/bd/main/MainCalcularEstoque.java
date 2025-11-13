package br.edu.ifpb.es.bd.main;

import br.edu.ifpb.es.bd.repository.ProdutoRepositoryJDBC;
import java.sql.SQLException;

public class MainCalcularEstoque {

    public static void main(String[] args) {
        ProdutoRepositoryJDBC repository = new ProdutoRepositoryJDBC();

        long categoriaIdParaTeste = 1L;

        try {
            System.out.println("Calculando o valor total do estoque para a categoria ID: " + categoriaIdParaTeste);

            double valorTotal = repository.getValorTotalEstoquePorCategoria(categoriaIdParaTeste);

            System.out.println("==================================================");
            System.out.printf(">>> O valor total do estoque é: R$ %.2f%n", valorTotal);
            System.out.println("==================================================");

        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao calcular o valor do estoque.");
            e.printStackTrace();
        }
    }
}