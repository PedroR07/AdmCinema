package CineMais.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import CineMais.controller.AdmController;

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
        new AdmController(this); // Inicializa o controlador e o associa a esta tela
        setLocationRelativeTo(null);
        setTitle("Área do Administrador");
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

    // Métodos para acessar os botões
    public JButton getSessoesButton() {
        return Sessoes;
    }

    public JButton getCatalogoButton() {
        return Catalogo;
    }

    public JButton getCardapioButton() {
        return Cardapio;
    }

    public JButton getVoltarButton() {
        return Voltar;
    }

    public JButton getSenhaButton() {
        return Senha;
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAdm().setVisible(true);
            }
        });
    }
}
