package CineMais;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
        setPreferredSize(new Dimension(600, 500)); // Dimensões ajustadas
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.X_AXIS));

        // Painel para a imagem do filme
        JLabel lblImagem = new JLabel();
        ImageIcon originalIcon = new ImageIcon("imagens/" + filme.getId() + ".png");
        Image img = originalIcon.getImage();
        Image scaledImg = img.getScaledInstance(160, 240, Image.SCALE_SMOOTH); // Imagem ajustada
        lblImagem.setIcon(new ImageIcon(scaledImg));
        panelInfo.add(lblImagem);
        panelInfo.add(Box.createHorizontalStrut(20)); // Espaço entre a imagem e as informações

        // Painel para as informações do filme
        JPanel panelDetalhes = new JPanel();
        panelDetalhes.setLayout(new BoxLayout(panelDetalhes, BoxLayout.Y_AXIS));

        JLabel lblNome = new JLabel("Nome: " + filme.getNome());
        JLabel lblDiretor = new JLabel("Diretor: " + filme.getDiretor());
        JLabel lblGenero = new JLabel("Gênero: " + filme.getGenero());
        JLabel lblAno = new JLabel("Ano: " + filme.getAno());

        panelDetalhes.add(lblNome);
        panelDetalhes.add(Box.createVerticalStrut(10));
        panelDetalhes.add(lblDiretor);
        panelDetalhes.add(Box.createVerticalStrut(10));
        panelDetalhes.add(lblGenero);
        panelDetalhes.add(Box.createVerticalStrut(10));
        panelDetalhes.add(lblAno);

        panelInfo.add(panelDetalhes);

        // Adiciona o painel com imagem e informações ao topo
        add(panelInfo, BorderLayout.NORTH);

        // Caixa de texto da sinopse
        JTextArea txtSinopse = new JTextArea(filme.getSinopse());
        txtSinopse.setLineWrap(true);
        txtSinopse.setWrapStyleWord(true);
        txtSinopse.setPreferredSize(new Dimension(550, 100)); // Ajustado para caber na nova largura
        txtSinopse.setEditable(false);
        add(new JScrollPane(txtSinopse), BorderLayout.CENTER);

        // Painel para os botões
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnCriarSessao = new JButton("Criar Sessão");
        btnCriarSessao.setBackground(new java.awt.Color(0, 123, 255));
        btnCriarSessao.setForeground(new java.awt.Color(255, 255, 255));
        btnCriarSessao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaAddSessao telaAddSessao = new TelaAddSessao(filme);
                telaAddSessao.setVisible(true);
            }
        });
        panelButtons.add(btnCriarSessao);

        JButton btnExcluirFilme = new JButton("Excluir Filme");
        btnExcluirFilme.setBackground(new java.awt.Color(220, 53, 69));
        btnExcluirFilme.setForeground(new java.awt.Color(255, 255, 255));
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
                        CatalogoFilmes catalogoFilmes = new CatalogoFilmes();
                        catalogoFilmes.setVisible(true);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao excluir o filme: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        panelButtons.add(btnExcluirFilme);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(new java.awt.Color(40, 167, 69));
        btnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                CatalogoFilmes catalogoFilmes = new CatalogoFilmes();
                catalogoFilmes.setVisible(true);
            }
        });
        panelButtons.add(btnVoltar);

        add(panelButtons, BorderLayout.SOUTH);
        pack();
    }
}
