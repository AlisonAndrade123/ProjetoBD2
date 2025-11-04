package br.edu.ifpb.es.bd.main;

import br.edu.ifpb.es.bd.model.Produto;
import br.edu.ifpb.es.bd.repository.ProdutoRepositoryJDBC;
import java.sql.SQLException;

public class MainProdutoRead {
    public static void main(String[] args) throws SQLException {
        ProdutoRepositoryJDBC produtoRepo = new ProdutoRepositoryJDBC();

        long idParaBuscar = 1L;

        System.out.println("Buscando o produto com ID " + idParaBuscar + "...");
        Produto produtoDoBanco = produtoRepo.findById(idParaBuscar);

        if (produtoDoBanco != null) {
            System.out.println("SUCESSO: Produto encontrado: " + produtoDoBanco);
            System.out.println("Nome da Categoria (via JOIN): " + produtoDoBanco.getCategoria().getNome());
        } else {
            System.err.println("ERRO: Produto com ID " + idParaBuscar + " não foi encontrado.");
        }
    }
}