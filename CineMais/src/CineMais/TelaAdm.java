package CineMais;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class TelaAdm extends JFrame {

    private JPanel jPanel3;
    private JPanel jPanel4;
    private JButton Sessoes;
    private JButton Catalogo;
    private JButton Cardapio;
    private JButton Voltar;
    private JButton Senha;
    private JLabel jLabel1;

    public TelaAdm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        jPanel3 = new JPanel();
        jPanel4 = new JPanel();
        Sessoes = new JButton("Sessões Ativas");
        Catalogo = new JButton("Catálogo");
        Cardapio = new JButton("Cardápio");
        Voltar = new JButton("Voltar");
        Senha = new JButton("Gerenciar Usuários");
        jLabel1 = new JLabel("Área do Administrador:");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel3.setLayout(new CardLayout());

        // Ajustando o tamanho dos botões
        Dimension buttonSize = new Dimension(150, 50);
        Sessoes.setPreferredSize(buttonSize);
        Catalogo.setPreferredSize(buttonSize);
        Cardapio.setPreferredSize(buttonSize);
        Senha.setPreferredSize(buttonSize);

        // Estilizando os botões
        Sessoes.setBackground(new Color(40, 167, 69));
        Sessoes.setForeground(Color.WHITE);
        Catalogo.setBackground(new Color(40, 167, 69));
        Catalogo.setForeground(Color.WHITE);
        Cardapio.setBackground(new Color(40, 167, 69));
        Cardapio.setForeground(Color.WHITE);
        Senha.setBackground(new Color(40, 167, 69));
        Senha.setForeground(Color.WHITE);
        
        Voltar.setBackground(new Color(220, 53, 69));
        Voltar.setForeground(Color.WHITE);

        // Ações dos botões
        Sessoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SessoesActionPerformed(evt);
            }
        });

        Catalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CatalogoActionPerformed(evt);
            }
        });

        Cardapio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CardapioActionPerformed(evt);
            }
        });

        Voltar.setFont(Voltar.getFont().deriveFont(12f));
        Voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                VoltarActionPerformed(evt);
            }
        });

        Senha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SenhaActionPerformed(evt);
            }
        });

        jLabel1.setFont(jLabel1.getFont().deriveFont(18f));
        jLabel1.setForeground(Color.BLACK);

        // Configurando o layout do painel principal
        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Voltar)
                    .addGap(20, 20, 20))
                .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(Sessoes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                            .addComponent(Senha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(Catalogo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                            .addComponent(Cardapio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(30, 30, 30))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(Voltar))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(Catalogo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Cardapio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(Sessoes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Senha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(40, 40, 40))
        );

        jPanel3.add(jPanel4, "card2");

        // Configurando o layout do content pane
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void VoltarActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
    }

    private void SessoesActionPerformed(ActionEvent evt) {
        TelaGerirSessoesAtivas telaGerirSessoesAtivas = new TelaGerirSessoesAtivas();
        this.setVisible(false); // Oculta a tela atual
        telaGerirSessoesAtivas.setVisible(true); // Exibe a nova tela
    }

    private void CardapioActionPerformed(ActionEvent evt) {
        TelaGestaoCardapio telaGestaoCardapio = new TelaGestaoCardapio();
        this.setVisible(false);
        telaGestaoCardapio.setVisible(true);
    }

    private void SenhaActionPerformed(ActionEvent evt) {
        TelaGestaoUsuarios telaGestaoUsuarios = new TelaGestaoUsuarios();
        this.setVisible(false);
        telaGestaoUsuarios.setVisible(true);
    }

    private void CatalogoActionPerformed(ActionEvent evt) {
        CatalogoFilmes catalog = new CatalogoFilmes();
        this.setVisible(false);
        catalog.setVisible(true);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAdm().setVisible(true);
            }
        });
    }
}
