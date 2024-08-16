package CineMais;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TelaFilmesEmCartaz extends JFrame {
    private JPanel mainPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton backButton;

    public TelaFilmesEmCartaz() {
        setTitle("Filmes em Cartaz");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Painel de busca
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchField = new JTextField(30);
        searchButton = new JButton("Buscar");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilmes(searchField.getText());
            }
        });
        searchPanel.add(new JLabel("Buscar:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Configuração do painel principal com rolagem
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Organizar verticalmente
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Adicionar botão "Voltar"
        backButton = new JButton("Voltar");
        backButton.setBackground(new Color(220, 53, 69)); // Vermelho
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela atual
                new TelaUser().setVisible(true); // Abre a tela TelaUser
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Carregar os filmes em cartaz
        loadFilmesEmCartaz();
    }

    private void loadFilmesEmCartaz() {
        String query = "SELECT s.`ID Sessao`, s.Sala, s.Horario, f.Nome, f.Sinopse, f.Genero, f.ID " +
                "FROM sessoes s JOIN filmografia f ON s.`ID Filme` = f.ID";

        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            // Limpar painel principal antes de adicionar novos filmes
            mainPanel.removeAll();

            while (rs.next()) {
                addFilmeToPanel(rs);
            }
            mainPanel.revalidate();
            mainPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchFilmes(String query) {
        String sql = "SELECT s.`ID Sessao`, s.Sala, s.Horario, f.Nome, f.Sinopse, f.Genero, f.ID " +
                "FROM sessoes s JOIN filmografia f ON s.`ID Filme` = f.ID " +
                "WHERE f.Nome LIKE ?";

        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + query + "%");
            ResultSet rs = pstmt.executeQuery();

            // Limpar painel principal antes de adicionar novos filmes
            mainPanel.removeAll();

            while (rs.next()) {
                addFilmeToPanel(rs);
            }
            mainPanel.revalidate();
            mainPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addFilmeToPanel(ResultSet rs) throws SQLException {
        int idFilme = rs.getInt("ID");
        int idSessao = rs.getInt("ID Sessao");
        String nomeFilme = rs.getString("Nome");
        String sinopseFilme = rs.getString("Sinopse");
        String generoFilme = rs.getString("Genero");
        int sala = rs.getInt("Sala");
        String horario = rs.getString("Horario");
    
        // Criar painel para a sessão
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        panel.setPreferredSize(new Dimension(950, 250));
        panel.setBackground(Color.WHITE);
    
        // Adicionar a imagem do filme
        JLabel imgLabel = new JLabel();
        ImageIcon imgIcon = new ImageIcon(new ImageIcon("imagens/" + idFilme + ".png").getImage().getScaledInstance(150,
                225, Image.SCALE_SMOOTH));
        imgLabel.setIcon(imgIcon);
        imgLabel.setPreferredSize(new Dimension(150, 225));
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        imgLabel.setVerticalAlignment(JLabel.CENTER);
        panel.add(imgLabel, BorderLayout.WEST);
    
        // Adicionar os dados do filme
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.setBackground(Color.WHITE);
    
        JLabel nomeLabel = new JLabel("<html><b></b> " + nomeFilme + "</html>");
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nomeLabel.setForeground(new Color(0, 102, 204)); // Azul escuro
    
        JLabel generoLabel = new JLabel("<html><b>Gênero:</b> " + generoFilme + "</html>");
        generoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        generoLabel.setForeground(Color.DARK_GRAY); // Cinza escuro
    
        JLabel salaLabel = new JLabel("<html><b>Sala:</b> " + sala + "</html>");
        salaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        salaLabel.setForeground(Color.DARK_GRAY); // Cinza escuro
    
        JLabel horarioLabel = new JLabel("<html><b>Horário:</b> " + horario + "</html>");
        horarioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        horarioLabel.setForeground(Color.DARK_GRAY); // Cinza escuro
    
        // Adicionar as labels ao painel de informações
        infoPanel.add(nomeLabel);
        infoPanel.add(generoLabel);
        infoPanel.add(salaLabel);
        infoPanel.add(horarioLabel);
    
        // Adicionar a sinopse
        JTextArea sinopseArea = new JTextArea(sinopseFilme);
        sinopseArea.setLineWrap(true);
        sinopseArea.setWrapStyleWord(true);
        sinopseArea.setEditable(false);
        sinopseArea.setBackground(Color.WHITE); // Ajuste da cor de fundo
        sinopseArea.setPreferredSize(new Dimension(600, 100));
        JScrollPane sinopseScroll = new JScrollPane(sinopseArea);
        sinopseScroll.setBorder(BorderFactory.createEmptyBorder());
        JPanel sinopsePanel = new JPanel();
        sinopsePanel.setLayout(new BorderLayout());
        sinopsePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sinopsePanel.add(new JLabel("<html><b>Sinopse:</b></html>"), BorderLayout.NORTH);
        sinopsePanel.add(sinopseScroll, BorderLayout.CENTER);
    
        // Adicionar tudo ao painel central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2, 10, 10)); // Ajuste do layout para compactar
        centerPanel.add(infoPanel);
        centerPanel.add(sinopsePanel);
        panel.add(centerPanel, BorderLayout.CENTER);
    
        // Verificar se a sessão está esgotada
        int poltronasOcupadas = getPoltronasOcupadas(idSessao);
        JButton buyButton;
        if (poltronasOcupadas >= 40) {
            // Criar botão "Esgotado"
            buyButton = new JButton("Esgotado");
            buyButton.setBackground(Color.GRAY); // Cinza para indicar esgotado
            buyButton.setForeground(Color.WHITE);
            buyButton.setFocusPainted(false);
            buyButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            buyButton.setFont(new Font("Arial", Font.BOLD, 14));
            buyButton.setEnabled(false); // Desabilitar botão
        } else {
            // Criar botão "Comprar Ingresso"
            buyButton = new JButton("Comprar Ingresso: R$ 35,00");
            buyButton.setBackground(new Color(40, 167, 69)); // Verde
            buyButton.setForeground(Color.WHITE);
            buyButton.setFocusPainted(false);
            buyButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            buyButton.setFont(new Font("Arial", Font.BOLD, 14));
    
            // Adicionar ação ao botão "Comprar Ingresso"
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buyTicket(idSessao, idFilme);
                }
            });
        }
    
        // Adicionar botão ao painel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(buyButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        // Adicionar o painel do filme ao painel principal
        mainPanel.add(panel);
    }
    
    

    private void buyTicket(int idSessao, int idFilme) {
        // Coletar informações do usuário
        String nome = JOptionPane.showInputDialog(this, "Digite o nome:");
        String telefone = JOptionPane.showInputDialog(this, "Digite o telefone:");
    
        if (nome == null || telefone == null || nome.trim().isEmpty() || telefone.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e telefone são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Verificar o número de poltronas ocupadas
        int poltronasOcupadas = getPoltronasOcupadas(idSessao);
        if (poltronasOcupadas >= 40) {
            JOptionPane.showMessageDialog(this, "Sessão Esgotada!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Inserir os dados na tabela `ingressos`
        String query = "INSERT INTO ingressos (`Nome da Pessoa`, `Telefone`, `ID Sessao`, `ID Filme`) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, nome);
            pstmt.setString(2, telefone);
            pstmt.setInt(3, idSessao);
            pstmt.setInt(4, idFilme);
    
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Incrementar o número de poltronas ocupadas
                incrementaPoltronasOcupadas(idSessao);
                JOptionPane.showMessageDialog(this, "Ingresso comprado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao comprar ingresso. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    private int getPoltronasOcupadas(int idSessao) {
        String query = "SELECT `Numero Poltronas` FROM sessoes WHERE `ID Sessao` = ?";
        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idSessao);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Numero Poltronas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    private void incrementaPoltronasOcupadas(int idSessao) {
        String query = "UPDATE sessoes SET `Numero Poltronas` = `Numero Poltronas` + 1 WHERE `ID Sessao` = ?";
        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idSessao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaFilmesEmCartaz().setVisible(true));
    }
}
