package CineMais.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

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

import CineMais.controller.AdicionarProdutosController;

public class TelaAdicionarProdutos extends JFrame {

    private JTable tableProdutos;
    private JSpinner spinnerQuantidade;
    private JButton btnAdicionar;
    private JButton btnVoltar;
    private AdicionarProdutosController controller;

    public TelaAdicionarProdutos(int idIngresso) {
        this.controller = new AdicionarProdutosController(idIngresso);
        setTitle("Adicionar Produtos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel panelMain = new JPanel(new BorderLayout());
        panelMain.setBackground(new Color(240, 240, 240));

        DefaultTableModel model = new DefaultTableModel(new String[]{"Produto", "Volume", "Preço"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableProdutos = new JTable(model);
        tableProdutos.setFont(new Font("Arial", Font.PLAIN, 14));
        tableProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableProdutos.setRowHeight(30);
        tableProdutos.setGridColor(new Color(220, 220, 220));
        tableProdutos.setShowHorizontalLines(true);
        tableProdutos.setShowVerticalLines(true);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
        tableProdutos.getColumnModel().getColumn(0).setCellRenderer(renderer);
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        tableProdutos.getColumnModel().getColumn(1).setCellRenderer(renderer);
        tableProdutos.getColumnModel().getColumn(2).setCellRenderer(renderer);

        tableProdutos.getTableHeader().setBackground(new Color(60, 141, 188));
        tableProdutos.getTableHeader().setForeground(Color.WHITE);
        tableProdutos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tableProdutos.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tableProdutos);
        panelMain.add(scrollPane, BorderLayout.CENTER);

        controller.loadProducts(model);

        JPanel panelControl = new JPanel();
        panelControl.setBackground(new Color(240, 240, 240));
        panelControl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        spinnerQuantidade = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinnerQuantidade.setFont(new Font("Arial", Font.PLAIN, 14));
        spinnerQuantidade.setPreferredSize(new Dimension(80, 30));

        btnAdicionar = new JButton("Comprar");
        btnAdicionar.setBackground(new Color(40, 167, 69));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAdicionar.setPreferredSize(new Dimension(200, 30));
        btnAdicionar.addActionListener(this::adicionarProdutos);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(new Color(220, 53, 69));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVoltar.setPreferredSize(new Dimension(200, 30));
        btnVoltar.addActionListener(e -> {
            dispose(); // Fecha a tela atual
            SwingUtilities.invokeLater(() -> new TelaConsultaIngresso().setVisible(true)); // Abre a tela de consulta de ingressos
        });

        panelControl.add(new JLabel("Quantidade:"));
        panelControl.add(spinnerQuantidade);
        panelControl.add(btnAdicionar);
        panelControl.add(btnVoltar);
        panelMain.add(panelControl, BorderLayout.SOUTH);

        add(panelMain);
    }

    private void adicionarProdutos(ActionEvent e) {
        int selectedRow = tableProdutos.getSelectedRow();
        if (selectedRow >= 0) {
            String nomeProduto = (String) tableProdutos.getValueAt(selectedRow, 0);
            double precoProduto = Double.parseDouble(((String) tableProdutos.getValueAt(selectedRow, 2)).replace("R$ ", "").replace(",", "."));
            int quantidade = (Integer) spinnerQuantidade.getValue();
            double valorTotal = precoProduto * quantidade;
            
            String produtoTexto = String.format("R$ %.2f - %s", precoProduto, nomeProduto);
            
            controller.adicionarProduto(nomeProduto, precoProduto, quantidade, valorTotal, produtoTexto);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto da tabela!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaAdicionarProdutos(1).setVisible(true));
    }
}
