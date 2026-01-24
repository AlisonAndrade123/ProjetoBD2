package br.edu.ifpb.es.bd.main;

import br.edu.ifpb.es.bd.model.Categoria;
import br.edu.ifpb.es.bd.model.Produto;
import br.edu.ifpb.es.bd.repository.ProdutoRepositoryJDBC;
import java.sql.SQLException;

public class MainProdutoCreate {
    public static void main(String[] args) throws SQLException {
        ProdutoRepositoryJDBC produtoRepo = new ProdutoRepositoryJDBC();

        long idDaCategoriaExistente = 1L;

        Categoria categoriaAssociada = new Categoria();
        categoriaAssociada.setId(idDaCategoriaExistente);

        Produto produto = new Produto();
        produto.setNome("Chave de Fenda (CRUD)");
        produto.setDescricao("Chave de fenda com ponta Philips.");
        produto.setPreco(15.00);
        produto.setQuantidade(120);
        produto.setCategoria(categoriaAssociada);

        produtoRepo.save(produto);

        System.out.println("Produto criado com sucesso e associado à Categoria de ID " + idDaCategoriaExistente);
        System.out.println("Objeto salvo: " + produto);
    }
}