package CineMais.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CineMais.model.Filme;


public class EditarSessaoController {

    private static final String URL = "jdbc:mysql://localhost:3306/cinemais";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para buscar filmes no banco de dados
    public List<Filme> buscarFilmes(String searchTerm) throws SQLException {
        List<Filme> filmes = new ArrayList<>();
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM filmografia WHERE Nome LIKE ?")) {
            stmt.setString(1, "%" + searchTerm + "%");
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("Nome");
                String diretor = rs.getString("Diretor");
                String genero = rs.getString("Genero");
                String ano = rs.getString("Ano");
                String sinopse = rs.getString("Sinopse");
                
                Filme filme = new Filme(id, nome, diretor, genero, ano, sinopse);
                filmes.add(filme);
            }
        }
        
        return filmes;
    }
}
