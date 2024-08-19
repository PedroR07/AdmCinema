package CineMais.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CineMais.model.ConexaoDB;
import CineMais.view.TelaAdm;
import CineMais.view.TelaGerirSessoesAtivas;
public class GerirSessoesAtivas {

    private TelaGerirSessoesAtivas tela;
    
    public GerirSessoesAtivas(TelaGerirSessoesAtivas tela) {
        this.tela = tela;
        initController();
    }

    private void initController() {
        // Adiciona o ActionListener para o botão 'Remover'
        tela.getRemoverButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerSessao();
            }
        });

        // Adiciona o ActionListener para o botão 'Voltar'
        tela.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTelaAdm();
            }
        });

        // Carregar sessões na tabela
        carregarSessoes();
    }

    public void carregarSessoes() {
        String sql = "SELECT `ID Sessao`, `Sala`, `Horario`, f.Nome AS Filme " +
                     "FROM sessoes s JOIN filmografia f ON s.`ID Filme` = f.ID";
        try (Connection conexao = ConexaoDB.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Limpar tabela antes de carregar novos dados
            tela.getTabelaModel().setRowCount(0);

            while (rs.next()) {
                tela.getTabelaModel().addRow(new Object[]{
                    rs.getInt("ID Sessao"),
                    rs.getInt("Sala"),
                    rs.getString("Horario"),
                    rs.getString("Filme")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removerSessao() {
        int linhaSelecionada = tela.getTabelaSessoes().getSelectedRow();
        if (linhaSelecionada >= 0) {
            int idSessao = (int) tela.getTabelaSessoes().getValueAt(linhaSelecionada, 0);
            String sql = "DELETE FROM sessoes WHERE `ID Sessao` = ?";
            try (Connection conexao = ConexaoDB.getConnection();
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, idSessao);
                stmt.executeUpdate();
                carregarSessoes(); // Atualizar a tabela após remoção
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Mostrar mensagem de erro
        }
    }

    private void voltarParaTelaAdm() {
        tela.setVisible(false);
        new TelaAdm().setVisible(true);
    }
}
