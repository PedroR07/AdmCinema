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

public class CatalogoFilmes extends JFrame {

    private JPanel panel;
    private JButton btnAdicionarFilmes;
    private JButton btnFechar;
    private JTextField searchField;
    private JButton searchButton;

    public CatalogoFilmes() {
        initComponents();
        setLocationRelativeTo(null);
        loadFilmografia(""); // Carrega a filmografia ao inicializar o JFrame
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
                loadFilmografia(searchTerm); // Carrega a filmografia com o termo de busca
            }
        });
        panelBotoesEsquerda.add(searchButton);

        // Botão "Adicionar Filmes"
        btnAdicionarFilmes = new JButton("Adicionar Filmes");
        btnAdicionarFilmes.setPreferredSize(new Dimension(130, 40));
        btnAdicionarFilmes.setBackground(new java.awt.Color(40, 167, 69));
        btnAdicionarFilmes.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarFilmes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaAddFilme telaAddFilme = new TelaAddFilme();
                telaAddFilme.setVisible(true);
                CatalogoFilmes.this.setVisible(false);
            }
        });
        panelBotoesEsquerda.add(btnAdicionarFilmes);

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
                CatalogoFilmes.this.setVisible(false);
                telaAdm.setVisible(true);
            }
        });
        panelFechar.add(btnFechar);

        add(panelBotoes, BorderLayout.NORTH);
        pack();
    }

    private void loadFilmografia(String searchTerm) {
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
                        TelaInfoFilme telaInfoFilme = new TelaInfoFilme(filme);
                        telaInfoFilme.setVisible(true);
                        CatalogoFilmes.this.setVisible(false);
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

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CatalogoFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new CatalogoFilmes().setVisible(true);
        });
    }
}
