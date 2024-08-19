package CineMais.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SessaoController {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cinemais";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Method to add a new session to the database
    public boolean adicionarSessao(int sala, String horario, int filmeId) {
        String sql = "INSERT INTO sessoes (Sala, `Numero Poltronas`, Horario, `ID Filme`) VALUES (?, 0, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sala);
            stmt.setString(2, horario);
            stmt.setInt(3, filmeId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
