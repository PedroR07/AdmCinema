package CineMais.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class TelaError extends javax.swing.JFrame {

    private JLabel jLabel1;
    private JLabel jLabel2;
    private javax.swing.JButton jButton1;

    public TelaError() {
        initComponents();
        setLocationRelativeTo(null); // Centraliza a tela na tela
        setResizable(false); // Define a janela como não redimensionável
        setTitle("Erro!");
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // Fontes mais visíveis
        jLabel1.setText("Senha/Usuário Incorreto(s)");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel2.setText("Tente novamente.");

        jButton1.setText("OK");
        jButton1.setForeground(Color.BLACK); // Texto branco
        jButton1.setFocusPainted(false); // Remove a borda de foco
        jButton1.setBorder(BorderFactory.createEmptyBorder()); // Remove a borda do botão
        jButton1.setOpaque(true); // Garante que a cor de fundo do botão seja visível
        jButton1.setPreferredSize(new Dimension(100, 40)); // Tamanho do botão
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        // Layout GridBagLayout
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(layout);

        // Configurações para jLabel1
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 10, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        add(jLabel1, gbc);

        // Configurações para jLabel2
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 20, 10, 20);
        add(jLabel2, gbc);

        // Configurações para jButton1
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 20, 20, 20);
        add(jButton1, gbc);

        pack(); // Ajusta o tamanho da janela para caber todos os componentes
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false); // Oculta a janela ao clicar no botão
    }

    /**
     * Configura o título da mensagem de erro.
     *
     * @param titulo O título da mensagem de erro.
     */
    public void setTitulo(String titulo) {
        jLabel1.setText(titulo);
    }

    /**
     * Configura a mensagem de erro.
     *
     * @param mensagem A mensagem de erro.
     */
    public void setMensagem(String mensagem) {
        jLabel2.setText(mensagem);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaError.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaError().setVisible(true);
            }
        });
    }
}
