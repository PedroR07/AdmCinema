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

public class CatalogoFilmes extends JFrame {

    private JPanel panel;

    public CatalogoFilmes() {
        initComponents();
        setLocationRelativeTo(null);
        loadFilmografia(); // Carrega a filmografia ao inicializar o JFrame
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new BorderLayout()); // Usando BorderLayout para adicionar o painel no centro

        panel = new JPanel();
        panel.setLayout(new FlowLayout()); // Ajuste o layout conforme necessário

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane ao centro do JFrame

        pack();
    }

    private void loadFilmografia() {
        List<Filme> filmes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinemais", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM filmografia");
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("Nome");
                String diretor = rs.getString("Diretor");
                // Remove acento circunflexo do Genero
                String genero = rs.getString("Genero").replaceAll("\\^", "");
                String ano = rs.getString("Ano");
                String sinopse = rs.getString("Sinopse");

                Filme filme = new Filme(nome, diretor, genero, ano, sinopse);
                filmes.add(filme);
            }

            // Limpa o painel antes de adicionar novos botões
            panel.removeAll();
            int i = 0;
            for (Filme filme : filmes) {
                i++;
                JButton button = new JButton(); // Texto do botão é deixado em branco

                // Adiciona uma imagem de fundo ao botão
                ImageIcon originalIcon = new ImageIcon("C:/Users/2023101202010006/Documents/NetBeansProjects/CineMais/imagens/" + i + ".png"); // Substitua pelo caminho da sua imagem
                Image img = originalIcon.getImage();
                Image scaledImg = img.getScaledInstance(210, 320, Image.SCALE_SMOOTH); // Redimensiona a imagem
                ImageIcon scaledIcon = new ImageIcon(scaledImg);
                button.setIcon(scaledIcon);

                button.setPreferredSize(new Dimension(220, 330)); // Define o tamanho do botão para combinar com a imagem
                button.setMargin(new Insets(0, 0, 0, 0)); // Remove margens ao redor do texto

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "Informações sobre o filme:\n" +
                                "Nome: " + filme.getNome() + "\n" +
                                "Diretor: " + filme.getDiretor() + "\n" +
                                "Genero: " + filme.getGenero() + "\n" +
                                "Ano: " + filme.getAno() + "\n" +
                                "Sinopse: " + filme.getSinopse());
                    }
                });
                
                panel.add(button);
            }

            // Atualiza o painel após adicionar todos os botões
            panel.revalidate();
            panel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage(),
                                        "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new CatalogoFilmes().setVisible(true);
        });
    }
}




