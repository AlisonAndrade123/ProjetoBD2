package br.edu.ifpb.es.bd.repository;

import br.edu.ifpb.es.bd.jdbc.ConnectionFactory;
import br.edu.ifpb.es.bd.model.Categoria;
import br.edu.ifpb.es.bd.model.Produto;

import java.sql.*;

public class ProdutoRepositoryJDBC {

    // MÉTODO: CREATE (criação de produto)
    public void save(Produto produto) throws SQLException {
        if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            throw new IllegalArgumentException("Produto deve ter uma categoria com ID para ser salvo.");
        }

        String sql = "INSERT INTO produtos (nome, descricao, preco, quantidade, categoria_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setLong(5, produto.getCategoria().getId());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    produto.setId(rs.getLong(1));
                }
            }
        }
    }

    // MÉTODO: READ (busca de produto por ID)
    public Produto findById(Long id) throws SQLException {
        String sql = "SELECT p.*, c.nome as categoria_nome " +
                "FROM produtos p " +
                "LEFT JOIN categorias c ON p.categoria_id = c.id " +
                "WHERE p.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getLong("categoria_id"));
                    categoria.setNome(rs.getString("categoria_nome"));

                    Produto produto = new Produto();
                    produto.setId(rs.getLong("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setCategoria(categoria);

                    return produto;
                }
            }
        }
        return null;
    }

    // MÉTODO: UPDATE (atualização de produto)
    public void update(Produto produto) throws SQLException {
        if (produto.getId() == null || produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            throw new IllegalArgumentException("Produto e sua Categoria precisam de um ID para serem atualizados.");
        }
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, preco = ?, quantidade = ?, categoria_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setLong(5, produto.getCategoria().getId());
            stmt.setLong(6, produto.getId());
            stmt.executeUpdate();
        }
    }

    // MÉTODO: DELETE (remoção de produto)
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}