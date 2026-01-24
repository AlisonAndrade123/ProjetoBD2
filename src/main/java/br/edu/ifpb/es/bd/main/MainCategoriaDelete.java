package br.edu.ifpb.es.bd.main;

import br.edu.ifpb.es.bd.repository.CategoriaRepositoryJDBC;
import java.sql.SQLException;

public class MainCategoriaDelete {
    public static void main(String[] args) throws SQLException {
        CategoriaRepositoryJDBC categoriaRepo = new CategoriaRepositoryJDBC();

        long idParaDeletar = 4L;

        System.out.println("Tentando deletar a categoria com ID " + idParaDeletar + "...");
        categoriaRepo.delete(idParaDeletar);
        System.out.println("Comando de delete executado com sucesso.");
        System.out.println("Verifique no banco de dados se o registro foi removido.");
    }
}