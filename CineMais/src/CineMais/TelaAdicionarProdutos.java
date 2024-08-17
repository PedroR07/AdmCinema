package CineMais;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TelaAdicionarProdutos extends JFrame {

    private JTable tableProdutos;
    private JSpinner spinnerQuantidade;
    private JButton btnAdicionar;
    private int idIngresso;

    public TelaAdicionarProdutos(int idIngresso) {
        this.idIngresso = idIngresso;
        setTitle("Adicionar Produtos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // Painel principal
        JPanel panelMain = new JPanel(new BorderLayout());
        panelMain.setBackground(new Color(240, 240, 240)); // Cor de fundo mais clara

        // Tabela de produtos
        DefaultTableModel model = new DefaultTableModel(new String[]{"Produto", "Volume", "Preço"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna as células não editáveis
            }
        };
        tableProdutos = new JTable(model);
        tableProdutos.setFont(new Font("Arial", Font.PLAIN, 14));
        tableProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableProdutos.setRowHeight(30);
        tableProdutos.setGridColor(new Color(220, 220, 220));
        tableProdutos.setShowHorizontalLines(true);
        tableProdutos.setShowVerticalLines(true);

        // Estilizar as colunas da tabela
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
        tableProdutos.getColumnModel().getColumn(0).setCellRenderer(renderer);

        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        tableProdutos.getColumnModel().getColumn(1).setCellRenderer(renderer);
        tableProdutos.getColumnModel().getColumn(2).setCellRenderer(renderer);

        // Adicionar cor de cabeçalho
        tableProdutos.getTableHeader().setBackground(new Color(60, 141, 188)); // Cor de cabeçalho
        tableProdutos.getTableHeader().setForeground(Color.WHITE);
        tableProdutos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tableProdutos.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tableProdutos);
        panelMain.add(scrollPane, BorderLayout.CENTER);

        // Carregar produtos
        loadProducts(model);

        // Painel de controle
        JPanel panelControl = new JPanel();
        panelControl.setBackground(new Color(240, 240, 240));
        panelControl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        spinnerQuantidade = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinnerQuantidade.setFont(new Font("Arial", Font.PLAIN, 14));
        spinnerQuantidade.setPreferredSize(new Dimension(80, 30));

        btnAdicionar = new JButton("Comprar");
        btnAdicionar.setBackground(new Color(40, 167, 69)); // Verde
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAdicionar.setPreferredSize(new Dimension(200, 30));
        btnAdicionar.addActionListener(this::adicionarProdutos);

        panelControl.add(new JLabel("Quantidade:"));
        panelControl.add(spinnerQuantidade);
        panelControl.add(btnAdicionar);
        panelMain.add(panelControl, BorderLayout.SOUTH);

        add(panelMain);
    }

    private void loadProducts(DefaultTableModel model) {
        try (Connection con = ConexaoDB.getConnection()) {
            String sql = "SELECT nome, valor, volume FROM produtos_cinema";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                double valor = rs.getDouble("valor");
                String volume = rs.getString("volume");

                // Formatar o valor para o formato R$ 00,00
                String valorFormatado = String.format("R$ %.2f", valor);

                model.addRow(new Object[]{nome, volume, valorFormatado});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void adicionarProdutos(ActionEvent e) {
        int selectedRow = tableProdutos.getSelectedRow();
        if (selectedRow >= 0) {
            // Obtém o nome e o preço do produto selecionado
            String nomeProduto = (String) tableProdutos.getValueAt(selectedRow, 0);
            double precoProduto = Double.parseDouble(((String) tableProdutos.getValueAt(selectedRow, 2)).replace("R$ ", "").replace(",", "."));
            int quantidade = (Integer) spinnerQuantidade.getValue();
            double valorTotal = precoProduto * quantidade;
            
            // Formata o texto do produto para adicionar à tabela
            String produtoTexto = String.format("R$ %.2f - %s", precoProduto, nomeProduto);
            
            try (Connection con = ConexaoDB.getConnection()) {
                // Atualiza o valor do ingresso
                String sqlAtualizarValor = "UPDATE ingressos SET Valor = Valor + ? WHERE `ID Ingresso` = ?";
                PreparedStatement stmtValor = con.prepareStatement(sqlAtualizarValor);
                stmtValor.setDouble(1, valorTotal);
                stmtValor.setInt(2, idIngresso);
                stmtValor.executeUpdate();
                
                // Adiciona o produto comprado ao campo "produtos comprados"
                String sqlAdicionarProduto = "SELECT `produtos comprados` FROM ingressos WHERE `ID Ingresso` = ?";
                PreparedStatement stmtSelect = con.prepareStatement(sqlAdicionarProduto);
                stmtSelect.setInt(1, idIngresso);
                ResultSet rs = stmtSelect.executeQuery();
                String produtosComprados = "";
                if (rs.next()) {
                    produtosComprados = rs.getString("produtos comprados");
                    // Adiciona o novo produto, pulando uma linha se houver produtos anteriores
                    if (produtosComprados != null && !produtosComprados.isEmpty()) {
                        produtosComprados += "\n\n" + produtoTexto;
                    } else {
                        produtosComprados = produtoTexto;
                    }
                }
                
                String sqlAtualizarProdutos = "UPDATE ingressos SET `produtos comprados` = ? WHERE `ID Ingresso` = ?";
                PreparedStatement stmtProdutos = con.prepareStatement(sqlAtualizarProdutos);
                stmtProdutos.setString(1, produtosComprados);
                stmtProdutos.setInt(2, idIngresso);
                stmtProdutos.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Produto adicionado ao ingresso com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                TelaConsultaIngresso telaConsult = new TelaConsultaIngresso();
                dispose();
                telaConsult.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto da tabela!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaAdicionarProdutos(1).setVisible(true)); // Exemplo com ID 1
    }
}
