package br.edu.ifpb.es.bd;

import br.edu.ifpb.es.bd.modelo.Categoria;
import br.edu.ifpb.es.bd.repository.CategoriaRepositoryJDBC;
import java.sql.SQLException;

public class MainCategoriaUpdate {
    public static void main(String[] args) throws SQLException {
        CategoriaRepositoryJDBC categoriaRepo = new CategoriaRepositoryJDBC();

        long idParaAtualizar = 1L;

        Categoria categoria = categoriaRepo.findById(idParaAtualizar);

        if (categoria != null) {
            System.out.println("Categoria encontrada: " + categoria);

            categoria.setNome("Eletrônicos ATUALIZADO (CRUD)");

            categoriaRepo.update(categoria);
            System.out.println("Comando de update executado. Categoria atualizada para: " + categoria);
        } else {
            System.err.println("ERRO: Categoria com ID " + idParaAtualizar + " não encontrada para atualizar.");
        }
    }
}