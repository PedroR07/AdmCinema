import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class MinhaTela extends JFrame {

    private JPasswordField passwordField;
    private JButton botaoEntrar;

    public MinhaTela() {
        // Define o título da janela
        setTitle("Logon");

        // Define o tamanho da janela em pixels (largura x altura)
        setSize(400, 300);

        // Define o que acontece quando a janela é fechada
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configura o layout da janela (opcional, por padrão é BorderLayout)
        // Aqui eu estou mudando para FlowLayout para melhor visualização dos componentes
        setLayout(new FlowLayout());

        // Cria os botões
        botaoEntrar = new JButton("Entrar");
        JButton botaoEscolherFilme = new JButton("Escolher Filme");
        JButton botaoLogin = new JButton("Login");

        // Cria uma caixa de texto (campo de senha)
        passwordField = new JPasswordField(20);
        passwordField.setVisible(false);
        botaoEntrar.setVisible(false);

        // Adiciona um evento de clique para o botão "Entrar"
        botaoEntrar.addActionListener(e -> {
            String textoInserido = new String(passwordField.getPassword());
            if (textoInserido.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Correto");
            } else {
                JOptionPane.showMessageDialog(this, "Errado");
            }
        });

        // Adiciona um evento de clique para o botão "Escolher Filme"
        botaoEscolherFilme.addActionListener(e -> {
            JFrame escolherFilmeFrame = new JFrame("Escolher Filme");
            escolherFilmeFrame.setSize(300, 200);
            escolherFilmeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            escolherFilmeFrame.setLocationRelativeTo(null);

            JPanel escolherFilmePanel = new JPanel();
            escolherFilmeFrame.add(escolherFilmePanel);
            escolherFilmePanel.add(new JLabel("Aqui é a página de escolha de filmes"));

            escolherFilmeFrame.setVisible(true);
        });

        // Adiciona um evento de clique para o botão "Login"
        botaoLogin.addActionListener(e -> {
            passwordField.setVisible(true);
            botaoEntrar.setVisible(true);
            revalidate();
            repaint();
        });

        // Adiciona os componentes à janela
        getContentPane().add(botaoEscolherFilme);
        getContentPane().add(botaoLogin);
        getContentPane().add(passwordField);
        getContentPane().add(botaoEntrar);

        // Centraliza a janela na tela
        setLocationRelativeTo(null);
    }
}
