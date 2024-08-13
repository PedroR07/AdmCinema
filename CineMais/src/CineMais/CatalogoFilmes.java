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
                int id = rs.getInt("ID");
                String nome = rs.getString("Nome");
                String diretor = rs.getString("Diretor");
                String genero = rs.getString("Genero").replaceAll("\\^", "");
                String ano = rs.getString("Ano");
                String sinopse = rs.getString("Sinopse");
    
                Filme filme = new Filme(id, nome, diretor, genero, ano, sinopse);
                filmes.add(filme);
            }
    
            panel.removeAll();
            for (Filme filme : filmes) {
                JButton button = new JButton();
                // Use o ID do filme para carregar a imagem correspondente
                ImageIcon originalIcon = new ImageIcon("C:/Users/2023101202010006/Documents/NetBeansProjects/CineMais/imagens/" + filme.getId() + ".png");
                Image img = originalIcon.getImage();
                Image scaledImg = img.getScaledInstance(210, 320, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImg);
                button.setIcon(scaledIcon);
    
                button.setPreferredSize(new Dimension(220, 330));
                button.setMargin(new Insets(0, 0, 0, 0));
    
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




