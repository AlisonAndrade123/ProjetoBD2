package br.edu.ifpb.es.bd;

import br.edu.ifpb.es.bd.modelo.Produto;
import br.edu.ifpb.es.bd.repository.ProdutoRepositoryJDBC;
import java.sql.SQLException;

public class MainProdutoUpdate {
    public static void main(String[] args) throws SQLException {
        ProdutoRepositoryJDBC produtoRepo = new ProdutoRepositoryJDBC();

        long idParaAtualizar = 1L;

        Produto produto = produtoRepo.findById(idParaAtualizar);

        if (produto != null) {
            System.out.println("Produto original encontrado: " + produto);

            produto.setNome("Martelo ATUALIZADO (CRUD)");
            produto.setPreco(39.99);

            produtoRepo.update(produto);
            System.out.println("\nComando de update executado. Produto atualizado para: " + produto);
        } else {
            System.err.println("ERRO: Produto com ID " + idParaAtualizar + " não encontrado para atualizar.");
        }
    }
}