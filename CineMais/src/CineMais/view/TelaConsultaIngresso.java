package CineMais.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import CineMais.model.ConexaoDB;

public class TelaConsultaIngresso extends JFrame {

    private JTextField txtTelefone;
    private JPanel panelIngressos;

    public TelaConsultaIngresso() {
        setTitle("Consulta de Ingresso");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);

        // Painel de busca
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label e campo de telefone
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        searchPanel.add(new JLabel("Telefone:"), gbc);

        txtTelefone = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchPanel.add(txtTelefone, gbc);

        // Botão Buscar Ingresso
        JButton btnBuscar = new JButton("Buscar Ingresso");
        btnBuscar.setBackground(new Color(40, 167, 69)); // Verde
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 14));
        btnBuscar.addActionListener(this::buscarIngressos);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        searchPanel.add(btnBuscar, gbc);

        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Painel de ingressos
        panelIngressos = new JPanel();
        panelIngressos.setLayout(new BoxLayout(panelIngressos, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelIngressos);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(new Color(220, 53, 69)); // Vermelho
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        btnVoltar.addActionListener(e -> {
            dispose();
            TelaUser telaUser = new TelaUser();
            telaUser.setVisible(true);
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(btnVoltar);
        bottomPanel.add(Box.createHorizontalGlue());
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void buscarIngressos(ActionEvent evt) {
        String telefone = txtTelefone.getText();
        panelIngressos.removeAll();

        try (Connection con = ConexaoDB.getConnection()) {
            // SQL para buscar ingressos e detalhes do filme
            String sql = "SELECT i.`ID Ingresso`, i.`Nome da Pessoa`, i.Telefone, i.`ID Sessao`, f.Nome AS NomeFilme, i.Valor "
                    + "FROM ingressos i "
                    + "JOIN filmografia f ON i.`ID Filme` = f.ID "
                    + "WHERE i.Telefone = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, telefone);
            ResultSet rs = stmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(this, "Nenhum ingresso encontrado para este telefone!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                while (rs.next()) {
                    int idIngresso = rs.getInt("ID Ingresso");
                    String nomePessoa = rs.getString("Nome da Pessoa");
                    String nomeFilme = rs.getString("NomeFilme");
                    double valorIngresso = rs.getDouble("Valor");

                    // Criar painel para cada ingresso
                    JPanel ingressoPanel = new JPanel(new GridBagLayout());
                    ingressoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(5, 5, 5, 5);

                    // Detalhes do ingresso
                    JTextArea txtDetalhesIngresso = new JTextArea(4, 30);
                    txtDetalhesIngresso.setEditable(false);
                    txtDetalhesIngresso.setText(
                            "Nome: " + nomePessoa + "\n" +
                                    "Telefone: " + telefone + "\n" +
                                    "ID Sessão: " + rs.getInt("ID Sessao") + "\n" +
                                    "Nome do Filme: " + nomeFilme + "\n" +
                                    "Valor: R$ " + String.format("%.2f", valorIngresso));
                    txtDetalhesIngresso.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
                    txtDetalhesIngresso.setFont(new Font("Arial", Font.PLAIN, 14));
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.gridwidth = 2;
                    gbc.fill = GridBagConstraints.BOTH;
                    ingressoPanel.add(txtDetalhesIngresso, gbc);

                    // Botão Imprimir Ingresso
                    JButton btnImprimir = new JButton("Imprimir Ingresso");
                    btnImprimir.setBackground(new Color(220, 53, 69)); // Vermelho
                    btnImprimir.setForeground(Color.WHITE);
                    btnImprimir.setFocusPainted(false);
                    btnImprimir.setFont(new Font("Arial", Font.BOLD, 14));
                    btnImprimir.addActionListener(e -> imprimirIngresso(idIngresso, valorIngresso));
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.gridwidth = 1;
                    gbc.fill = GridBagConstraints.NONE;
                    gbc.anchor = GridBagConstraints.WEST;
                    ingressoPanel.add(btnImprimir, gbc);

                    // Botão Comprar Comida
                    JButton btnComprarComida = new JButton("Comprar Comida");
                    btnComprarComida.setBackground(new Color(23, 162, 184)); // Azul
                    btnComprarComida.setForeground(Color.WHITE);
                    btnComprarComida.setFocusPainted(false);
                    btnComprarComida.setFont(new Font("Arial", Font.BOLD, 14));
                    btnComprarComida.addActionListener(e -> adicionarProduto(idIngresso));
                    gbc.gridx = 1;
                    gbc.gridy = 1;
                    gbc.fill = GridBagConstraints.NONE;
                    gbc.anchor = GridBagConstraints.EAST;
                    ingressoPanel.add(btnComprarComida, gbc);

                    panelIngressos.add(ingressoPanel);
                    panelIngressos.add(Box.createVerticalStrut(10));
                    panelIngressos.add(new JSeparator(JSeparator.HORIZONTAL));
                }
                panelIngressos.revalidate();
                panelIngressos.repaint();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void imprimirIngresso(int idIngresso, double valorTotal) {
        try (Connection con = ConexaoDB.getConnection()) {
            // Atualiza o valor do ingresso no banco de dados
            String sqlUpdate = "UPDATE ingressos SET Valor = ? WHERE `ID Ingresso` = ?";
            try (PreparedStatement stmt = con.prepareStatement(sqlUpdate)) {
                stmt.setDouble(1, valorTotal);
                stmt.setInt(2, idIngresso);
                stmt.executeUpdate();
            }
    
            // Recupera os detalhes do ingresso atualizado
            String sqlSelectIngresso = "SELECT * FROM ingressos WHERE `ID Ingresso` = ?";
            String sqlSelectFilme = "SELECT Nome FROM filmografia WHERE ID = ?";
            String sqlSelectSessao = "SELECT Horario FROM sessoes WHERE `ID Sessao` = ?";
    
            try (PreparedStatement stmtIngresso = con.prepareStatement(sqlSelectIngresso);
                    PreparedStatement stmtFilme = con.prepareStatement(sqlSelectFilme);
                    PreparedStatement stmtSessao = con.prepareStatement(sqlSelectSessao)) {
    
                stmtIngresso.setInt(1, idIngresso);
                ResultSet rsIngresso = stmtIngresso.executeQuery();
    
                if (rsIngresso.next()) {
                    int idFilme = rsIngresso.getInt("ID Filme");
                    int idSessao = rsIngresso.getInt("ID Sessao");
    
                    // Obtém o nome do filme
                    stmtFilme.setInt(1, idFilme);
                    ResultSet rsFilme = stmtFilme.executeQuery();
                    String nomeFilme = rsFilme.next() ? rsFilme.getString("Nome") : "Desconhecido";
    
                    // Obtém o horário da sessão
                    stmtSessao.setInt(1, idSessao);
                    ResultSet rsSessao = stmtSessao.executeQuery();
                    String horarioSessao = rsSessao.next() ? rsSessao.getString("Horario") : "Desconhecido";
    
                    // Cria o arquivo de recibo
                    File dir = new File("recibos");
                    if (!dir.exists()) {
                        dir.mkdir(); // Cria o diretório se não existir
                    }
    
                    File file = new File(dir, "ingresso_" + idIngresso + ".txt");
                    LocalDate data = LocalDate.now();
                    try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                        writer.println("CineMais ----------------------- " + data);
                        writer.println("---------------- Ingresso -----------------");
                        writer.println("Filme: " + nomeFilme);
                        writer.println("Horário da Sessão: " + horarioSessao);
                        writer.println("Cliente: " + rsIngresso.getString("Nome da Pessoa"));
                        writer.println("Telefone: " + rsIngresso.getString("Telefone"));
                        writer.println("ID do Ingresso: " + rsIngresso.getInt("ID Ingresso"));
                        writer.println("ID Sessão: " + idSessao);
                        writer.println("------------------ Gastos -----------------");
                        String produtosComprados = rsIngresso.getString("Produtos Comprados");
                        writer.println(produtosComprados);
                        writer.println("-------------------------------------------");
                        writer.println("Total: R$ " + String.format("%.2f", valorTotal));
                        writer.println("-------------------------------------------");
                        writer.println("Obrigado por escolher nosso cinema!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
    
                    JOptionPane.showMessageDialog(null, "Ingresso atualizado e pronto para impressão!", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void adicionarProduto(int idIngresso) {
        TelaAdicionarProdutos telaAdicionarProdutos = new TelaAdicionarProdutos(idIngresso);
        TelaConsultaIngresso.this.setVisible(false);
        telaAdicionarProdutos.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaConsultaIngresso().setVisible(true));
    }
}
