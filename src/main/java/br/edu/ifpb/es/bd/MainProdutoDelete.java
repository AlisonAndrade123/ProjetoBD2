package br.edu.ifpb.es.bd;

import br.edu.ifpb.es.bd.repository.ProdutoRepositoryJDBC;
import java.sql.SQLException;

public class MainProdutoDelete {
    public static void main(String[] args) throws SQLException {
        ProdutoRepositoryJDBC produtoRepo = new ProdutoRepositoryJDBC();

        long idParaDeletar = 1L;

        System.out.println("Tentando deletar o produto com ID " + idParaDeletar + "...");
        produtoRepo.delete(idParaDeletar);
        System.out.println("Comando de delete executado com sucesso.");
        System.out.println("Verifique no banco de dados se o registro foi removido.");
    }
}