package CineMais;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaGerirSessoesAtivas extends JFrame {

    private JPanel jPanel1;
    private JButton voltar;
    private JButton remover;
    private JLabel jLabel1;
    private JTable tabelaSessoes;
    private JScrollPane scrollPane;
    private DefaultTableModel tabelaModel;

    public TelaGerirSessoesAtivas() {
        initComponents();
        setLocationRelativeTo(null);
        carregarSessoes();
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        voltar = new JButton("Voltar");
        remover = new JButton("Remover");
        jLabel1 = new JLabel("Sessões Ativas:");
        tabelaSessoes = new JTable();
        scrollPane = new JScrollPane(tabelaSessoes);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Gerenciar Sessões Ativas");

        // Configurar a tabela
        tabelaModel = new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID Sessão", "Sala", "Horário", "Filme"
            }
        );
        tabelaSessoes.setModel(tabelaModel);
        tabelaSessoes.setFillsViewportHeight(true);
        tabelaSessoes.setPreferredScrollableViewportSize(new Dimension(600, 300));
        scrollPane.setViewportView(tabelaSessoes);

        // Estilizando os botões
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        voltar.setBackground(new Color(220, 53, 69)); // Vermelho
        voltar.setForeground(Color.WHITE);
        voltar.setFont(buttonFont);
        voltar.setPreferredSize(new Dimension(120, 40));
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        remover.setBackground(new Color(255, 193, 7)); // Amarelo
        remover.setForeground(Color.BLACK);
        remover.setFont(buttonFont);
        remover.setPreferredSize(new Dimension(120, 40));
        remover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                removerActionPerformed(evt);
            }
        });

        // Estilizando o JLabel
        jLabel1.setFont(new Font("Arial", Font.BOLD, 18));
        jLabel1.setForeground(new Color(0, 51, 102));

        // Configurar o layout do painel principal
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20)
                    .addComponent(voltar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(remover)
                    .addGap(20))
                .addGroup(GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                    .addGap(20)
                    .addComponent(jLabel1)
                    .addGap(20))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGap(20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createSequentialGroup()
                .addGap(20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(voltar)
                    .addComponent(remover))
                .addGap(20)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(20)
        );

        // Configurando o layout do content pane
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void carregarSessoes() {
        String sql = "SELECT `ID Sessao`, `Sala`, `Horario`, f.Nome AS Filme " +
                     "FROM sessoes s JOIN filmografia f ON s.`ID Filme` = f.ID";
        try (Connection conexao = ConexaoDB.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Limpar tabela antes de carregar novos dados
            tabelaModel.setRowCount(0);

            while (rs.next()) {
                tabelaModel.addRow(new Object[]{
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

    private void removerActionPerformed(ActionEvent evt) {
        int linhaSelecionada = tabelaSessoes.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int idSessao = (int) tabelaSessoes.getValueAt(linhaSelecionada, 0);
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

    private void voltarActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        TelaAdm telaAdm = new TelaAdm();
        telaAdm.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaGerirSessoesAtivas().setVisible(true);
            }
        });
    }
}
