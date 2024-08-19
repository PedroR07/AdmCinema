package CineMais.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CineMais.controller.GerirSessoesAtivas;

public class TelaGerirSessoesAtivas extends JFrame {

    private JPanel jPanel1;
    private JButton voltar;
    private JButton remover;
    private JLabel jLabel1;
    private JTable tabelaSessoes;
    private JScrollPane scrollPane;
    private DefaultTableModel tabelaModel;

    public TelaGerirSessoesAtivas() {
        initComponents();
        setLocationRelativeTo(null);
        new GerirSessoesAtivas(this);  // Inicializa o controlador
        setTitle("Sessões Ativas");
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        voltar = new JButton("Voltar");
        remover = new JButton("Remover");
        jLabel1 = new JLabel("Sessões Ativas:");
        tabelaSessoes = new JTable();
        scrollPane = new JScrollPane(tabelaSessoes);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Gerenciar Sessões Ativas");

        // Configurar a tabela
        tabelaModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ID Sessão", "Sala", "Horário", "Filme"
            }
        );
        tabelaSessoes.setModel(tabelaModel);
        tabelaSessoes.setFillsViewportHeight(true);
        tabelaSessoes.setPreferredScrollableViewportSize(new Dimension(600, 300));
        scrollPane.setViewportView(tabelaSessoes);

        // Estilizando os botões
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        voltar.setBackground(new Color(220, 53, 69)); // Vermelho
        voltar.setForeground(Color.WHITE);
        voltar.setFont(buttonFont);
        voltar.setPreferredSize(new Dimension(120, 40));

        remover.setBackground(new Color(255, 193, 7)); // Amarelo
        remover.setForeground(Color.BLACK);
        remover.setFont(buttonFont);
        remover.setPreferredSize(new Dimension(120, 40));

        // Estilizando o JLabel
        jLabel1.setFont(new Font("Arial", Font.BOLD, 18));
        jLabel1.setForeground(new Color(0, 51, 102));

        // Configurar o layout do painel principal
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20)
                    .addComponent(voltar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(remover)
                    .addGap(20))
                .addGroup(GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                    .addGap(20)
                    .addComponent(jLabel1)
                    .addGap(20))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGap(20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createSequentialGroup()
                .addGap(20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(voltar)
                    .addComponent(remover))
                .addGap(20)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(20)
        );

        // Configurando o layout do content pane
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    public JButton getVoltarButton() {
        return voltar;
    }

    public JButton getRemoverButton() {
        return remover;
    }

    public JTable getTabelaSessoes() {
        return tabelaSessoes;
    }

    public DefaultTableModel getTabelaModel() {
        return tabelaModel;
    }

    // Método para atualizar a tabela, caso seja necessário fora do controller
    public void atualizarTabela() {
        new GerirSessoesAtivas(this).carregarSessoes();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaGerirSessoesAtivas().setVisible(true);
            }
        });
    }
}
