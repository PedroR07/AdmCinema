package CineMais.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import CineMais.model.ConexaoDB;

public class ColetorLixoIngressos {

    public static void executarColeta() {
        String querySessoes = "SELECT `ID Sessao` FROM sessoes";
        String queryIngressos = "SELECT `ID Ingresso`, `ID Sessao` FROM ingressos";
        String deleteIngresso = "DELETE FROM ingressos WHERE `ID Ingresso` = ?";

        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement psSessoes = conn.prepareStatement(querySessoes);
                PreparedStatement psIngressos = conn.prepareStatement(queryIngressos);
                PreparedStatement psDelete = conn.prepareStatement(deleteIngresso)) {

            // Primeiro, pegar todos os IDs de sessões válidos
            ResultSet rsSessoes = psSessoes.executeQuery();
            HashSet<Integer> sessoesValidas = new HashSet<>();

            while (rsSessoes.next()) {
                sessoesValidas.add(rsSessoes.getInt("ID Sessao"));
            }

            // Agora, verificar os ingressos e deletar os que têm IDs de sessões inválidas
            ResultSet rsIngressos = psIngressos.executeQuery();

            while (rsIngressos.next()) {
                int idIngresso = rsIngressos.getInt("ID Ingresso");
                int idSessao = rsIngressos.getInt("ID Sessao");

                if (!sessoesValidas.contains(idSessao)) {
                    // Se o ID da sessão não é válido, deletar o ingresso
                    psDelete.setInt(1, idIngresso);
                    psDelete.executeUpdate();
                    System.out.println("Ingresso com ID " + idIngresso + " deletado.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        executarColeta();
    }
}
