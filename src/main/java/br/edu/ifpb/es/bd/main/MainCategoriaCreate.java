package br.edu.ifpb.es.bd.main;

import br.edu.ifpb.es.bd.model.Categoria;
import br.edu.ifpb.es.bd.repository.CategoriaRepositoryJDBC;
import java.sql.SQLException;

public class MainCategoriaCreate {

    public static void main(String[] args) {
        CategoriaRepositoryJDBC repository = new CategoriaRepositoryJDBC();

        Categoria novaCategoria = new Categoria();
        novaCategoria.setNome("Headsets");
        novaCategoria.setDescricao("Fones de ouvido com microfone para gamers e profissionais.");

        try {
            repository.save(novaCategoria);

            System.out.println("==================================================");
            System.out.println(">>> Categoria '" + novaCategoria.getNome() + "' salva com sucesso!");
            System.out.println(">>> ID Gerado: " + novaCategoria.getId());
            System.out.println("==================================================");

        } catch (SQLException e) {
            System.err.println("!!! Ocorreu um erro ao salvar a categoria.");
            e.printStackTrace();
        }
    }
}