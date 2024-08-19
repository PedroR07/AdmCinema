package CineMais.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import CineMais.model.ConexaoDB;
import CineMais.view.TelaFilmesEmCartaz;
import CineMais.view.TelaUser;

public class FilmesEmCartazController {
    private TelaFilmesEmCartaz telaFilmesEmCartaz;

    public FilmesEmCartazController(TelaFilmesEmCartaz telaFilmesEmCartaz) {
        this.telaFilmesEmCartaz = telaFilmesEmCartaz;
        this.telaFilmesEmCartaz.setSearchButtonListener(new SearchButtonListener());
        this.telaFilmesEmCartaz.setBackButtonListener(new BackButtonListener());
        loadFilmesEmCartaz();
    }

    private void loadFilmesEmCartaz() {
        String query = "SELECT s.`ID Sessao`, s.Sala, s.Horario, f.Nome, f.Sinopse, f.Genero, f.ID " +
                "FROM sessoes s JOIN filmografia f ON s.`ID Filme` = f.ID";
        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            telaFilmesEmCartaz.clearMainPanel();

            while (rs.next()) {
                telaFilmesEmCartaz.addFilmeToPanel(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchFilmes(telaFilmesEmCartaz.getSearchQuery());
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            telaFilmesEmCartaz.dispose();
            new TelaUser().setVisible(true);
        }
    }

    private void searchFilmes(String query) {
        String sql = "SELECT s.`ID Sessao`, s.Sala, s.Horario, f.Nome, f.Sinopse, f.Genero, f.ID " +
                "FROM sessoes s JOIN filmografia f ON s.`ID Filme` = f.ID " +
                "WHERE f.Nome LIKE ?";
        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + query + "%");
            ResultSet rs = pstmt.executeQuery();

            telaFilmesEmCartaz.clearMainPanel();

            while (rs.next()) {
                telaFilmesEmCartaz.addFilmeToPanel(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buyTicket(int idSessao, int idFilme) {
        String nome = JOptionPane.showInputDialog(telaFilmesEmCartaz, "Digite o nome:");
        String telefone = JOptionPane.showInputDialog(telaFilmesEmCartaz, "Digite o telefone:");

        if (nome == null || telefone == null || nome.trim().isEmpty() || telefone.trim().isEmpty()) {
            JOptionPane.showMessageDialog(telaFilmesEmCartaz, "Nome e telefone são obrigatórios.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int poltronasOcupadas = getPoltronasOcupadas(idSessao);
        if (poltronasOcupadas >= 40) {
            JOptionPane.showMessageDialog(telaFilmesEmCartaz, "Sessão Esgotada!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String valor = "35.00";
        String descricaoProduto = "R$ 35,00 - Ingresso";

        String query = "INSERT INTO ingressos (`Nome da Pessoa`, `Telefone`, `ID Sessao`, `ID Filme`, `valor`, `Produtos Comprados`) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, telefone);
            pstmt.setInt(3, idSessao);
            pstmt.setInt(4, idFilme);
            pstmt.setString(5, valor);
            pstmt.setString(6, descricaoProduto);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                incrementaPoltronasOcupadas(idSessao);
                JOptionPane.showMessageDialog(telaFilmesEmCartaz, "Ingresso comprado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                telaFilmesEmCartaz.dispose();
                new TelaFilmesEmCartaz().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(telaFilmesEmCartaz, "Erro ao comprar ingresso. Tente novamente.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getPoltronasOcupadas(int idSessao) {
        String query = "SELECT `Numero Poltronas` FROM sessoes WHERE `ID Sessao` = ?";
        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idSessao);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Numero Poltronas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void incrementaPoltronasOcupadas(int idSessao) {
        String updateQuery = "UPDATE sessoes SET `Numero Poltronas` = `Numero Poltronas` + 1 WHERE `ID Sessao` = ?";

        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setInt(1, idSessao);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Número de poltronas ocupadas incrementado com sucesso.");
            } else {
                System.out.println("Falha ao incrementar o número de poltronas ocupadas.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
