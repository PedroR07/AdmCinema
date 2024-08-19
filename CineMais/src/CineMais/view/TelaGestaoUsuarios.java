package CineMais.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import CineMais.controller.GestaoUsuariosController;

public class TelaGestaoUsuarios extends JFrame {

    private JLabel jLabel1;
    private JLabel jLabelUsuario;
    private JLabel jLabelSenha;
    private JLabel jLabelNomeCompleto;
    private JButton jButtonAdicionar;
    private JButton jButtonRemover;
    private JButton jButtonAlterarSenha;
    private JButton jButtonConsultar;
    private JButton jButtonVoltar; // Novo botão de Voltar
    private JTextArea jTextAreaUsuarios;
    private JTextField jTextFieldUsuario;
    private JTextField jTextFieldSenha;
    private JTextField jTextFieldNomeCompleto;

    private GestaoUsuariosController controller;

    public TelaGestaoUsuarios() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        controller = new GestaoUsuariosController();
        setTitle("Editor de Usuários");
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 500)); // Define a largura em 800 e altura em 500

        jLabel1 = new JLabel("Gestão de Usuários:");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Aumenta o tamanho da fonte

        jLabelUsuario = new JLabel("Nome de Usuário:");
        jLabelSenha = new JLabel("Senha:");
        jLabelNomeCompleto = new JLabel("Nome Completo:");

        jButtonAdicionar = new JButton("Adicionar Novo Usuário");
        jButtonAdicionar.setBackground(new Color(40, 167, 69)); // Verde
        jButtonAdicionar.setForeground(Color.WHITE);
        jButtonAdicionar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jButtonAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarUsuario();
            }
        });

        jButtonRemover = new JButton("Remover Usuário");
        jButtonRemover.setBackground(new Color(255, 193, 7)); // Amarelo
        jButtonRemover.setForeground(Color.WHITE);
        jButtonRemover.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jButtonRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerUsuario();
            }
        });

        jButtonAlterarSenha = new JButton("Alterar Senha do Meu Usuário");
        jButtonAlterarSenha.setBackground(new Color(0, 123, 255)); // Azul
        jButtonAlterarSenha.setForeground(Color.WHITE);
        jButtonAlterarSenha.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jButtonAlterarSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarSenha();
            }
        });

        jButtonConsultar = new JButton("Consultar Usuários Cadastrados");
        jButtonConsultar.setBackground(new Color(0, 123, 255)); // Azul
        jButtonConsultar.setForeground(Color.WHITE);
        jButtonConsultar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jButtonConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarUsuarios();
            }
        });

        jButtonVoltar = new JButton("Voltar"); // Novo botão de Voltar
        jButtonVoltar.setBackground(new Color(220, 53, 69)); // Vermelho
        jButtonVoltar.setForeground(Color.WHITE);
        jButtonVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jButtonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltar();
            }
        });

        jTextAreaUsuarios = new JTextArea();
        jTextAreaUsuarios.setEditable(false);
        JScrollPane scrollPaneUsuarios = new JScrollPane(jTextAreaUsuarios);

        jTextFieldUsuario = new JTextField(25);
        jTextFieldSenha = new JTextField(25);
        jTextFieldNomeCompleto = new JTextField(25);

        // Inicialmente os campos de adicionar usuário estarão visíveis
        jTextFieldUsuario.setVisible(true);
        jTextFieldSenha.setVisible(true);
        jTextFieldNomeCompleto.setVisible(true);
        jLabelUsuario.setVisible(true);
        jLabelSenha.setVisible(true);
        jLabelNomeCompleto.setVisible(true);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelUsuario)
                                                        .addComponent(jTextFieldUsuario)
                                                        .addComponent(jLabelSenha)
                                                        .addComponent(jTextFieldSenha)
                                                        .addComponent(jLabelNomeCompleto)
                                                        .addComponent(jTextFieldNomeCompleto)
                                                        .addComponent(jButtonAdicionar, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonRemover, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonAlterarSenha, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonConsultar, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(scrollPaneUsuarios)
                                                        .addComponent(jButtonVoltar, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)) // Adiciona
                                                                                                            // o botão
                                                                                                            // de Voltar
                                                .addContainerGap()))));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(20, 20, 20)
                                .addComponent(jLabelUsuario)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelSenha)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelNomeCompleto)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNomeCompleto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAdicionar)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRemover)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAlterarSenha)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonConsultar)
                                .addGap(18, 18, 18)
                                .addComponent(scrollPaneUsuarios, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonVoltar)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }

    private void adicionarUsuario() {
        String usuario = jTextFieldUsuario.getText().trim();
        String senha = jTextFieldSenha.getText().trim();
        String nomeCompleto = jTextFieldNomeCompleto.getText().trim();
    
        if (usuario.isEmpty() || senha.isEmpty() || nomeCompleto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        try {
            controller.adicionarUsuario(usuario, senha, nomeCompleto);
            JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso!");
            jTextFieldUsuario.setText("");
            jTextFieldSenha.setText("");
            jTextFieldNomeCompleto.setText("");
        } catch (SQLException e) {
            if (e.getMessage().contains("Nome de usuário já existe!")) {
                JOptionPane.showMessageDialog(this, "O nome de usuário já existe. Escolha outro.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void removerUsuario() {
        String usuario = jTextFieldUsuario.getText().trim();
        String senha = jTextFieldSenha.getText().trim();
    
        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Os campos Nome de Usuário e Senha devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        try {
            boolean usuarioExiste = controller.verificarSenha(usuario, senha);
            if (!usuarioExiste) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado ou senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            controller.removerUsuario(usuario, senha);
            JOptionPane.showMessageDialog(this, "Usuário removido com sucesso!");
            jTextFieldUsuario.setText("");
            jTextFieldSenha.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao remover usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void alterarSenha() {
        try {
            // Solicita o nome de usuário
            String usuario = JOptionPane.showInputDialog(this, "Digite o nome de usuário:");
            if (usuario == null || usuario.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome de usuário não pode ser vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Solicita a senha antiga
            String senhaAntiga = JOptionPane.showInputDialog(this, "Digite a senha antiga:");
            if (senhaAntiga == null || senhaAntiga.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Senha antiga não pode ser vazia!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Solicita a nova senha
            String senhaNova = JOptionPane.showInputDialog(this, "Digite a nova senha:");
            if (senhaNova == null || senhaNova.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Senha nova não pode ser vazia!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
    
            // Verifica se a senha antiga está correta
            boolean senhaCorreta = controller.verificarSenha(usuario, senhaAntiga);
            if (!senhaCorreta) {
                JOptionPane.showMessageDialog(this, "Senha antiga incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Altera a senha
            controller.alterarSenha(usuario, senhaAntiga, senhaNova); // Corrigido para incluir a senha antiga e nova
            JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar senha: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

    private void consultarUsuarios() {
        try {
            String usuarios = controller.consultarUsuarios();
            jTextAreaUsuarios.setText(usuarios);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar usuários: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltar() {
        // Código para voltar à tela anterior
        dispose(); 
        new TelaAdm().setVisible(true);
    }
}
