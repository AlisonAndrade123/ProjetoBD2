package br.edu.ifpb.es.bd.repository;

import br.edu.ifpb.es.bd.jdbc.ConnectionFactory;
import br.edu.ifpb.es.bd.modelo.Categoria;

import java.sql.*;

public class CategoriaRepositoryJDBC {

    // MÉTODO: CREATE (nova categoria)
    public void save(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categorias (nome) VALUES (?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, categoria.getNome());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    categoria.setId(rs.getLong(1));
                }
            }
        }
    }

    // MÉTODO: READ (busca de categoria por ID)
    public Categoria findById(Long id) throws SQLException {
        String sql = "SELECT * FROM categorias WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getLong("id"));
                    categoria.setNome(rs.getString("nome"));
                    return categoria;
                }
            }
        }
        return null;
    }

    // MÉTODO: UPDATE (atualização de categoria)
    public void update(Categoria categoria) throws SQLException {
        if (categoria.getId() == null) {
            throw new IllegalArgumentException("Categoria precisa de um ID para ser atualizada.");
        }
        String sql = "UPDATE categorias SET nome = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setLong(2, categoria.getId());
            stmt.executeUpdate();
        }
    }

    // MÉTODO: DELETE (remoção de categoria)
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM categorias WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}