package CineMais.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestaoUsuariosController {

    private static final String URL = "jdbc:mysql://localhost:3306/cinemais";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para adicionar usuário, verificando se o nome de usuário já existe
    public void adicionarUsuario(String usuario, String senha, String nomeCompleto) throws SQLException {
        if (usuarioExiste(usuario)) {
            throw new SQLException("Nome de usuário já existe!");
        }

        String sql = "INSERT INTO users (Usuário, Senha, Pessoa) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            stmt.setString(3, nomeCompleto);

            stmt.executeUpdate();
        }
    }

    // Método para verificar se um usuário com o mesmo nome já existe
    private boolean usuarioExiste(String usuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE Usuário = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Método para remover usuário, exigindo a senha correspondente
    public void removerUsuario(String usuario, String senha) throws SQLException {
        String sql = "DELETE FROM users WHERE Usuário = ? AND Senha = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Usuário não encontrado ou senha incorreta.");
            }
        }
    }

    // Método para alterar a senha de um usuário
    public void alterarSenha(String usuario, String senhaAntiga, String senhaNova) throws SQLException {
        String sql = "UPDATE users SET Senha = ? WHERE Usuário = ? AND Senha = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, senhaNova);
            stmt.setString(2, usuario);
            stmt.setString(3, senhaAntiga);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Senha antiga incorreta ou usuário não encontrado.");
            }
        }
    }

    // Método para verificar se a senha do usuário está correta
    public boolean verificarSenha(String usuario, String senha) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE Usuário = ? AND Senha = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Método para consultar todos os usuários cadastrados
    public String consultarUsuarios() throws SQLException {
        StringBuilder resultado = new StringBuilder();
        String sql = "SELECT Usuário, Pessoa FROM users";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String usuario = rs.getString("Usuário");
                String nomeCompleto = rs.getString("Pessoa");
                resultado.append("Usuário: ").append(usuario)
                        .append(", Nome Completo: ").append(nomeCompleto)
                        .append("\n");
            }
        }
        return resultado.toString();
    }
}
