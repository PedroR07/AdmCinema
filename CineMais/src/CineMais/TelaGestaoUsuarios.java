package CineMais;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.UIManager;

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

    public TelaGestaoUsuarios() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600)); // Aumenta a largura da janela

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
                            .addComponent(jButtonAdicionar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonRemover, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAlterarSenha, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonConsultar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollPaneUsuarios)
                            .addComponent(jButtonVoltar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)) // Adiciona o botão de Voltar
                        .addContainerGap())))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(jLabelUsuario)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSenha)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNomeCompleto)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNomeCompleto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonAdicionar)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRemover)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAlterarSenha)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonConsultar)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneUsuarios, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonVoltar) // Adiciona o botão de Voltar
                .addContainerGap())
        );

        pack();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/cinemais", "root", "");
    }

    private void adicionarUsuario() {
        String usuario = jTextFieldUsuario.getText();
        String senha = jTextFieldSenha.getText();
        String nomeCompleto = jTextFieldNomeCompleto.getText();

        if (usuario.isEmpty() || senha.isEmpty() || nomeCompleto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (Usuário, Senha, Pessoa) VALUES (?, ?, ?)")) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            stmt.setString(3, nomeCompleto);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso!");
            jTextFieldUsuario.setText("");
            jTextFieldSenha.setText("");
            jTextFieldNomeCompleto.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerUsuario() {
        String usuario = JOptionPane.showInputDialog(this, "Digite o nome do usuário a ser removido:");
        if (usuario == null || usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome de usuário não pode ser vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE Usuário = ?")) {
    
            stmt.setString(1, usuario);
            int rowsAffected = stmt.executeUpdate();
    
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Usuário removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao remover usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void alterarSenha() {
        String usuario = JOptionPane.showInputDialog(this, "Digite seu nome de usuário:");
        String senhaAtual = JOptionPane.showInputDialog(this, "Digite sua senha atual:");
        String novaSenha = JOptionPane.showInputDialog(this, "Digite sua nova senha:");
    
        if (usuario == null || senhaAtual == null || novaSenha == null || usuario.isEmpty() || senhaAtual.isEmpty() || novaSenha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE users SET Senha = ? WHERE Usuário = ? AND Senha = ?")) {
    
            stmt.setString(1, novaSenha);
            stmt.setString(2, usuario);
            stmt.setString(3, senhaAtual);
            int rowsAffected = stmt.executeUpdate();
    
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Nome de usuário ou senha atual incorretos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao alterar senha: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void consultarUsuarios() {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
             ResultSet rs = stmt.executeQuery()) {
    
            jTextAreaUsuarios.setText("");
            while (rs.next()) {
                String usuario = rs.getString("Usuário");
                String nomeCompleto = rs.getString("Pessoa");
                jTextAreaUsuarios.append("Usuário: " + usuario + " - Nome: " + nomeCompleto + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao consultar usuários: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void voltar() {
        // Voltar para a tela anterior
        new TelaAdm().setVisible(true);
        this.dispose();
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(() -> new TelaGestaoUsuarios().setVisible(true));
    }
}