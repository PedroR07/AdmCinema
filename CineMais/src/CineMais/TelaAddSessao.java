package CineMais;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaAddSessao extends JFrame {

    private JTextField txtSala;
    private JTextField txtHorario;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private Filme filme;

    public TelaAddSessao(Filme filme) {
        this.filme = filme;
        initComponents();
        setLocationRelativeTo(null); // Posiciona a tela no centro
    }

    private void initComponents() {
        setTitle("Adicionar Sessão");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(400, 200));
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new java.awt.GridLayout(3, 2, 5, 5));

        inputPanel.add(new JLabel("Sala:"));
        txtSala = new JTextField();
        inputPanel.add(txtSala);

        inputPanel.add(new JLabel("Horário:"));
        txtHorario = new JTextField();
        inputPanel.add(txtHorario);

        panel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sala = txtSala.getText();
                String horario = txtHorario.getText();

                if (sala.isEmpty() || horario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinemais", "root", "");
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO sessoes (Sala, Horario, `ID Filme`) VALUES (?, ?, ?)")) {
                    stmt.setInt(1, Integer.parseInt(sala));
                    stmt.setString(2, horario);
                    stmt.setInt(3, filme.getId());
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Sessão adicionada com sucesso.");
                    dispose(); // Fecha a janela
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar sessão: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(btnSalvar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela
            }
        });
        buttonPanel.add(btnCancelar);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public static void main(String[] args) {
        // For testing
        Filme testeFilme = new Filme(1, "Test Movie", "Director", "Genre", "2024", "Description");
        new TelaAddSessao(testeFilme).setVisible(true);
    }
}
