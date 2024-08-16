package CineMais;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TelaEditarSessao extends JFrame {

    private JPanel panel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton btnSelecionarFilme;
    private JButton btnFechar;

    public TelaEditarSessao() {
        initComponents();
        setLocationRelativeTo(null);
        carregarFilmes(""); // Usando o método sobrecarregado
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // Painel para os botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new BorderLayout(10, 10));

        // Painel para os botões à esquerda
        JPanel panelBotoesEsquerda = new JPanel();
        panelBotoesEsquerda.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBotoes.add(panelBotoesEsquerda, BorderLayout.CENTER);

        // Campo de busca
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 40));
        panelBotoesEsquerda.add(searchField);

        // Botão de busca
        searchButton = new JButton("Buscar");
        searchButton.setPreferredSize(new Dimension(130, 40));
        searchButton.setBackground(new java.awt.Color(0, 123, 255));
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                carregarFilmes(searchTerm); // Usando a versão sobrecarregada
            }
        });
        panelBotoesEsquerda.add(searchButton);

        // Botão "Selecionar Filme"
        btnSelecionarFilme = new JButton("Selecionar Filme");
        btnSelecionarFilme.setPreferredSize(new Dimension(150, 40));
        btnSelecionarFilme.setBackground(new java.awt.Color(40, 167, 69));
        btnSelecionarFilme.setForeground(new java.awt.Color(255, 255, 255));
        btnSelecionarFilme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicionar lógica para selecionar um filme
            }
        });
        panelBotoesEsquerda.add(btnSelecionarFilme);

        // Painel para o botão "Fechar"
        JPanel panelFechar = new JPanel();
        panelFechar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelBotoes.add(panelFechar, BorderLayout.EAST);

        // Botão "Fechar"
        btnFechar = new JButton("Fechar");
        btnFechar.setPreferredSize(new Dimension(130, 40));
        btnFechar.setBackground(new java.awt.Color(220, 53, 69));
        btnFechar.setForeground(new java.awt.Color(255, 255, 255));
        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaAdm telaAdm = new TelaAdm();
                TelaEditarSessao.this.setVisible(false);
                telaAdm.setVisible(true);
            }
        });
        panelFechar.add(btnFechar);

        add(panelBotoes, BorderLayout.NORTH);
        pack();
    }

    private void carregarFilmes(String searchTerm, JPanel panel) {
        panel.removeAll(); // Limpa os botões existentes
        List<Filme> filmes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinemais", "root", "");
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

            for (Filme filme : filmes) {
                JButton button = new JButton();
                ImageIcon originalIcon = new ImageIcon("imagens/" + filme.getId() + ".png");
                Image img = originalIcon.getImage();
                Image scaledImg = img.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImg);
                button.setIcon(scaledIcon);

                button.setPreferredSize(new Dimension(210, 320));
                button.setMargin(new Insets(0, 0, 0, 0));

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Adicionar lógica para selecionar o filme
                        JOptionPane.showMessageDialog(null, "Selecionado: " + filme.getNome());
                    }
                });

                panel.add(button);
            }

            panel.revalidate();
            panel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Sobrecarga do método carregarFilmes
    private void carregarFilmes(String searchTerm) {
        carregarFilmes(searchTerm, panel); // Chama o método principal com o painel padrão
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEditarSessao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new TelaEditarSessao().setVisible(true);
        });
    }
}
