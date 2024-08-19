package CineMais.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CineMais.controller.SessaoController;
import CineMais.model.ConexaoDB;
import CineMais.model.Filme;

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
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int numeroSala;
                try {
                    numeroSala = Integer.parseInt(sala);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número de sala inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (numeroSala < 1 || numeroSala > 5) {
                    JOptionPane.showMessageDialog(null, "O número da sala deve estar entre 1 e 5.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isHorarioValido(horario)) {
                    JOptionPane.showMessageDialog(null, "Horário inválido. Use o formato HH:MM ou HHhMM.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (isSalaOcupada(numeroSala, horario)) {
                    JOptionPane.showMessageDialog(null, "A sala já está ocupada nesse horário.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isEspacoSuficiente(numeroSala, horario)) {
                    JOptionPane.showMessageDialog(null,
                            "Não há um espaço de pelo menos 4 horas entre a nova sessão e a sessão anterior na mesma sala.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SessaoController controller = new SessaoController();
                boolean success = controller.adicionarSessao(numeroSala, horario, filme.getId());
                if (success) {
                    JOptionPane.showMessageDialog(null, "Sessão adicionada com sucesso.");
                    dispose(); // Fecha a janela
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar sessão.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
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

    private boolean isHorarioValido(String horario) {
        // Regex para verificar os formatos HH:MM ou HHhMM
        String regex = "^([01]\\d|2[0-3]):([0-5]\\d)$|^([01]\\d|2[0-3])h([0-5]\\d)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(horario);
        return matcher.matches();
    }

    private boolean isEspacoSuficiente(int sala, String horarioNovo) {
        String query = "SELECT Horario FROM sessoes WHERE Sala = ? ORDER BY Horario DESC LIMIT 1";

        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, sala);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String horarioAnterior = rs.getString("Horario");
                if (horarioAnterior != null) {
                    int minutosAnterior = converterParaMinutos(horarioAnterior);
                    int minutosNovo = converterParaMinutos(horarioNovo);
                    return minutosNovo - minutosAnterior >= 240;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao verificar espaço entre sessões: " + ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    private int converterParaMinutos(String horario) {
        String[] partes = horario.split(":|h");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        return horas * 60 + minutos;
    }

    private boolean isSalaOcupada(int sala, String horario) {
        String query = "SELECT COUNT(*) FROM sessoes WHERE Sala = ? AND Horario = ?";

        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, sala);
            stmt.setString(2, horario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao verificar disponibilidade da sala: " + ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static void main(String[] args) {
        Filme testeFilme = new Filme(1, "Test Movie", "Director", "Genre", "2024", "Description");
        new TelaAddSessao(testeFilme).setVisible(true);
    }
}
