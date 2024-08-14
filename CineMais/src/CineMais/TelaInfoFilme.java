package CineMais;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TelaInfoFilme extends JFrame {

    private Filme filme;

    public TelaInfoFilme(Filme filme) {
        this.filme = filme;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        setLayout(new BorderLayout());

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

        JLabel lblNome = new JLabel("Nome: " + filme.getNome());
        JLabel lblDiretor = new JLabel("Diretor: " + filme.getDiretor());
        JLabel lblGenero = new JLabel("Gênero: " + filme.getGenero());
        JLabel lblAno = new JLabel("Ano: " + filme.getAno());

        JTextArea txtSinopse = new JTextArea(filme.getSinopse());
        txtSinopse.setLineWrap(true);
        txtSinopse.setWrapStyleWord(true);
        txtSinopse.setPreferredSize(new Dimension(500, 200));
        txtSinopse.setEditable(false);

        panelInfo.add(lblNome);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblDiretor);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblGenero);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(lblAno);
        panelInfo.add(Box.createVerticalStrut(10));
        panelInfo.add(new JScrollPane(txtSinopse));

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnCriarSessao = new JButton("Criar Sessão");
        btnCriarSessao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaAddSessao telaAddSessao = new TelaAddSessao(filme);
                telaAddSessao.setVisible(true);
            }
        });
        panelButtons.add(btnCriarSessao);

        JButton btnExcluirFilme = new JButton("Excluir Filme");
        btnExcluirFilme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o filme " + filme.getNome() + "?", "Excluir Filme", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinemais", "root", "");
                         PreparedStatement stmt = conn.prepareStatement("DELETE FROM filmografia WHERE ID = ?")) {
                        stmt.setInt(1, filme.getId());
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Filme excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // Fecha a janela de informações do filme
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao excluir o filme: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        panelButtons.add(btnExcluirFilme);

        add(panelInfo, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
        pack();
    }
}
