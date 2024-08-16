package CineMais;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class TelaConsultaIngresso extends JFrame {

    private JTextField txtTelefone;
    private JTextArea txtDetalhesIngresso;
    private JComboBox<String> cmbProdutos;
    private JLabel lblValorTotal;
    private JButton btnAdicionarProduto, btnImprimir;
    private double valorTotal = 0.0;
    private int idIngresso;

    public TelaConsultaIngresso() {
        setTitle("Consulta de Ingresso");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label e campo de telefone
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(new JLabel("Telefone:"), gbc);

        txtTelefone = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtTelefone, gbc);

        // Botão Buscar Ingresso
        JButton btnBuscar = new JButton("Buscar Ingresso");
        btnBuscar.setBackground(new Color(40, 167, 69)); // Verde
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 14));
        btnBuscar.addActionListener(this::buscarIngresso);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(btnBuscar, gbc);

        // Área de detalhes do ingresso
        txtDetalhesIngresso = new JTextArea(5, 40);
        txtDetalhesIngresso.setEditable(false);
        txtDetalhesIngresso.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        txtDetalhesIngresso.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(txtDetalhesIngresso);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane, gbc);

        // ComboBox de produtos
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(new JLabel("Selecione um Produto:"), gbc);

        cmbProdutos = new JComboBox<>();
        carregarProdutos();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(cmbProdutos, gbc);

        // Botão Adicionar Produto
        btnAdicionarProduto = new JButton("Adicionar Produto");
        btnAdicionarProduto.setBackground(new Color(23, 162, 184)); // Azul
        btnAdicionarProduto.setForeground(Color.WHITE);
        btnAdicionarProduto.setFocusPainted(false);
        btnAdicionarProduto.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnAdicionarProduto.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdicionarProduto.addActionListener(this::adicionarProduto);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(btnAdicionarProduto, gbc);

        // Label Valor Total
        lblValorTotal = new JLabel("Valor Total: R$ 0.00");
        lblValorTotal.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(lblValorTotal, gbc);

        // Botão Imprimir
        btnImprimir = new JButton("Imprimir Ingresso");
        btnImprimir.setBackground(new Color(220, 53, 69)); // Vermelho
        btnImprimir.setForeground(Color.WHITE);
        btnImprimir.setFocusPainted(false);
        btnImprimir.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnImprimir.setFont(new Font("Arial", Font.BOLD, 14));
        btnImprimir.addActionListener(this::imprimirIngresso);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(btnImprimir, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void buscarIngresso(ActionEvent evt) {
        String telefone = txtTelefone.getText();
        try (Connection con = ConexaoDB.getConnection()) {
            String sql = "SELECT * FROM ingressos WHERE Telefone = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, telefone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idIngresso = rs.getInt("ID Ingresso");
                txtDetalhesIngresso.setText("Nome: " + rs.getString("Nome da Pessoa") +
                        "\nTelefone: " + rs.getString("Telefone") +
                        "\nID Sessão: " + rs.getInt("ID Sessao") +
                        "\nID Filme: " + rs.getInt("ID Filme") +
                        "\nValor Atual: R$ " + rs.getDouble("Valor"));
                valorTotal = rs.getDouble("Valor");
                atualizarValorTotal();
            } else {
                JOptionPane.showMessageDialog(this, "Ingresso não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carregarProdutos() {
        try (Connection con = ConexaoDB.getConnection()) {
            String sql = "SELECT nome, valor FROM produtos_cinema";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cmbProdutos.addItem(rs.getString("nome") + " - R$ " + rs.getDouble("valor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void adicionarProduto(ActionEvent evt) {
        String selectedItem = (String) cmbProdutos.getSelectedItem();
        if (selectedItem != null) {
            String[] parts = selectedItem.split(" - R\\$ ");
            double valorProduto = Double.parseDouble(parts[1]);
            valorTotal += valorProduto;
            atualizarValorTotal();
        }
    }

    private void atualizarValorTotal() {
        lblValorTotal.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));
    }

    private void imprimirIngresso(ActionEvent evt) {
        try (Connection con = ConexaoDB.getConnection()) {
            String sql = "UPDATE ingressos SET Valor = ? WHERE `ID Ingresso` = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDouble(1, valorTotal);
            stmt.setInt(2, idIngresso);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Ingresso atualizado e pronto para impressão!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaConsultaIngresso().setVisible(true));
    }
}
