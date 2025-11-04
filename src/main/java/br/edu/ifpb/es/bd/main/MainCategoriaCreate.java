package br.edu.ifpb.es.bd.main;

import br.edu.ifpb.es.bd.model.Categoria;
import br.edu.ifpb.es.bd.repository.CategoriaRepositoryJDBC;
import java.sql.SQLException;

public class MainCategoriaCreate {
    public static void main(String[] args) throws SQLException {
        CategoriaRepositoryJDBC categoriaRepo = new CategoriaRepositoryJDBC();

        Categoria novaCategoria = new Categoria();
        novaCategoria.setNome("Eletrônicos (CRUD)");

        categoriaRepo.save(novaCategoria);

        System.out.println("Categoria criada com sucesso. Verifique o ID no banco de dados.");
        System.out.println("Objeto salvo: " + novaCategoria);
    }
}