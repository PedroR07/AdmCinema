
package CineMais.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FilmeController {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cinemais";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Method to add a new movie to the database
    public boolean adicionarFilme(String titulo, String genero, String diretor, String sinopse, String caminhoImagem) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO filmografia (titulo, genero, diretor, sinopse, caminhoImagem) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, titulo);
                stmt.setString(2, genero);
                stmt.setString(3, diretor);
                stmt.setString(4, sinopse);
                stmt.setString(5, caminhoImagem);
                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
