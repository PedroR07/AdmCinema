package CineMais;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaAdm extends JFrame {

    private JPanel jPanel3;
    private JPanel jPanel4;
    private JButton Sessões;
    private JButton Catálogo;
    private JButton Cardápio;
    private JButton Sair;
    private JButton Senha;
    private JLabel jLabel1;

    public TelaAdm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        jPanel3 = new JPanel();
        jPanel4 = new JPanel();
        Sessões = new JButton("Sessões");
        Catálogo = new JButton("Catálogo");
        Cardápio = new JButton("Cardápio");
        Sair = new JButton("<");
        Senha = new JButton("Gerenciar Usuários");
        jLabel1 = new JLabel("Área do Administrador:");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel3.setLayout(new CardLayout());

        Sessões.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SessõesActionPerformed(evt);
            }
        });

        Catálogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CatálogoActionPerformed(evt);
            }
        });

        Cardápio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CardápioActionPerformed(evt);
            }
        });

        Sair.setFont(Sair.getFont().deriveFont(12f));
        Sair.setForeground(java.awt.Color.RED);
        Sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SairActionPerformed(evt);
            }
        });

        Senha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SenhaActionPerformed(evt);
            }
        });

        jLabel1.setFont(jLabel1.getFont().deriveFont(18f));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(Sair)
                .addGap(23, 23, 23))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Catálogo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sessões, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Cardápio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Senha, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Sair))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cardápio, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Catálogo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Senha, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sessões, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107))
        );

        jPanel3.add(jPanel4, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void SairActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
    }

    private void SessõesActionPerformed(ActionEvent evt) {
        // Implementar ação para o botão Sessões
    }

    private void CardápioActionPerformed(ActionEvent evt) {
        TelaGestaoCardapio telaGestaoCardapio = new TelaGestaoCardapio();
        this.setVisible(false);
        telaGestaoCardapio.setVisible(true);
    }

    private void SenhaActionPerformed(ActionEvent evt) {
        TelaGestaoUsuarios telaGestaoUsuarios = new TelaGestaoUsuarios();
        this.setVisible(false);
        telaGestaoUsuarios.setVisible(true);
    }

    private void CatálogoActionPerformed(ActionEvent evt) {
        CatalogoFilmes catalog = new CatalogoFilmes();
        this.setVisible(false);
        catalog.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAdm().setVisible(true);
            }
        });
    }
}
