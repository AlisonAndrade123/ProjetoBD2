package br.edu.ifpb.es.bd.main;

import br.edu.ifpb.es.bd.model.Categoria;
import br.edu.ifpb.es.bd.repository.CategoriaRepositoryJDBC;
import java.sql.SQLException;

public class MainCategoriaRead {
    public static void main(String[] args) throws SQLException {
        CategoriaRepositoryJDBC categoriaRepo = new CategoriaRepositoryJDBC();

        long idParaBuscar = 1L;

        System.out.println("Buscando a categoria com ID " + idParaBuscar + "...");
        Categoria categoriaDoBanco = categoriaRepo.findById(idParaBuscar);

        if (categoriaDoBanco != null) {
            System.out.println("SUCESSO: Categoria encontrada: " + categoriaDoBanco);
        } else {
            System.err.println("ERRO: Categoria com ID " + idParaBuscar + " não foi encontrada.");
        }
    }
}