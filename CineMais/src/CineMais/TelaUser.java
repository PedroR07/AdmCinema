package CineMais;

import java.awt.Color;
import java.awt.Font;

public class TelaUser extends javax.swing.JFrame {

    public TelaUser() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnVerFilmes = new javax.swing.JButton();
        btnTicket = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new Color(240, 240, 240)); // Cor de fundo clara

        lblTitulo.setText("Área do Cliente:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); // Centraliza o texto

        btnVerFilmes.setText("Ver Filmes em Cartaz");
        btnVerFilmes.setBackground(new Color(40, 167, 69)); // Verde
        btnVerFilmes.setForeground(Color.WHITE);
        btnVerFilmes.setFont(new Font("Arial", Font.BOLD, 14));
        btnVerFilmes.addActionListener(evt -> btnVerFilmesActionPerformed(evt));

        btnTicket.setText("Tenho um Ingresso");
        btnTicket.setBackground(new Color(0, 123, 255)); // Azul
        btnTicket.setForeground(Color.WHITE);
        btnTicket.setFont(new Font("Arial", Font.BOLD, 14));
        btnTicket.addActionListener(evt -> btnTicketActionPerformed(evt));

        btnVoltar.setText("Voltar");
        btnVoltar.setBackground(new Color(220, 53, 69)); // Vermelho
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 12));
        btnVoltar.addActionListener(evt -> btnVoltarActionPerformed(evt));

        // Layout
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVerFilmes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)))
                    .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(lblTitulo)
                    .addGap(18, 18, 18)
                    .addComponent(btnVerFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void btnVerFilmesActionPerformed(java.awt.event.ActionEvent evt) {
        TelaFilmesEmCartaz telaFilmesEmCartaz = new TelaFilmesEmCartaz();
        this.setVisible(false); // Fecha a tela atual
        telaFilmesEmCartaz.setVisible(true); // Abre a nova tela
    }

    private void btnTicketActionPerformed(java.awt.event.ActionEvent evt) {
        TelaConsultaIngresso telaConsultaIngresso = new TelaConsultaIngresso();
        this.setVisible(false);
        telaConsultaIngresso.setVisible(true);
    }

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        TelaInicial telaInicial = new TelaInicial();
        this.setVisible(false); // Fecha a tela atual
        telaInicial.setVisible(true); // Abre a tela inicial
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaUser().setVisible(true));
    }

    private javax.swing.JButton btnVerFilmes;
    private javax.swing.JButton btnTicket;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
}
