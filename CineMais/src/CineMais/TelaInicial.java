package CineMais;

import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaInicial extends javax.swing.JFrame {

    public TelaInicial() {
        initComponents();
        customizeButtons();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnTelaInicial = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pnTelaLogon = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton3 = new javax.swing.JButton();
        pnAreaAdm = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new java.awt.CardLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18));
        jLabel1.setText("Cine+");

        jButton1.setText("Cliente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Adm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTelaInicialLayout = new javax.swing.GroupLayout(pnTelaInicial);
        pnTelaInicial.setLayout(pnTelaInicialLayout);
        pnTelaInicialLayout.setHorizontalGroup(
            pnTelaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTelaInicialLayout.createSequentialGroup()
                .addGroup(pnTelaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTelaInicialLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jLabel1))
                    .addGroup(pnTelaInicialLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addGroup(pnTelaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(164, 164, 164))
        );
        pnTelaInicialLayout.setVerticalGroup(
            pnTelaInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTelaInicialLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        jPanel1.add(pnTelaInicial, "card2");

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 18));
        jLabel2.setText("Logon");

        jLabel3.setText("ID de Administrador:");

        jLabel4.setText("Senha:");

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jTextPane1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTextPane1InputMethodTextChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTextPane1);

        jButton3.setText("Entrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTelaLogonLayout = new javax.swing.GroupLayout(pnTelaLogon);
        pnTelaLogon.setLayout(pnTelaLogonLayout);
        pnTelaLogonLayout.setHorizontalGroup(
            pnTelaLogonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTelaLogonLayout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addGroup(pnTelaLogonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField1)
                    .addComponent(jScrollPane1))
                .addGap(146, 146, 146))
            .addGroup(pnTelaLogonLayout.createSequentialGroup()
                .addGroup(pnTelaLogonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTelaLogonLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel2))
                    .addGroup(pnTelaLogonLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnTelaLogonLayout.setVerticalGroup(
            pnTelaLogonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTelaLogonLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel1.add(pnTelaLogon, "card3");

        javax.swing.GroupLayout pnAreaAdmLayout = new javax.swing.GroupLayout(pnAreaAdm);
        pnAreaAdm.setLayout(pnAreaAdmLayout);
        pnAreaAdmLayout.setHorizontalGroup(
            pnAreaAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        pnAreaAdmLayout.setVerticalGroup(
            pnAreaAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        jPanel1.add(pnAreaAdm, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        String senha = new String(jPasswordField1.getPassword());
        String user = jTextPane1.getText();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexaoDB.getConnection();
            String sql = "SELECT * FROM users WHERE `Usuário` = ? AND `Senha` = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                TelaAdm telaAdm = new TelaAdm();
                telaAdm.setVisible(true);
                this.setVisible(false);
            } else {
                TelaError telaError = new TelaError();
                telaError.setVisible(true);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Implementar lógica para botão Cliente
        // Exemplo: Mostrar uma tela de cliente ou qualquer outra funcionalidade desejada
        TelaUser telaCliente = new TelaUser();
        telaCliente.setVisible(true);
        this.setVisible(false);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "card3");
    }

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Implementar lógica para senha
    }

    private void jTextPane1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
        // Implementar lógica para texto
    }

    private void customizeButtons() {
        jButton1.setBackground(new java.awt.Color(40, 167, 69)); // Verde
        jButton1.setForeground(java.awt.Color.WHITE);
        jButton2.setBackground(new java.awt.Color(220, 53, 69)); // Vermelho
        jButton2.setForeground(java.awt.Color.WHITE);
        jButton3.setBackground(new java.awt.Color(0, 123, 255)); // Azul
        jButton3.setForeground(java.awt.Color.WHITE);
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnAreaAdm;
    private javax.swing.JPanel pnTelaInicial;
    private javax.swing.JPanel pnTelaLogon;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
}
