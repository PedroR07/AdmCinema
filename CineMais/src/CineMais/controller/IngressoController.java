
package CineMais.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngressoController {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cinemais";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Method to fetch ticket information based on phone number
    public ResultSet consultarIngressoPorTelefone(String telefone) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM ingressos WHERE telefone_cliente = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, telefone);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
