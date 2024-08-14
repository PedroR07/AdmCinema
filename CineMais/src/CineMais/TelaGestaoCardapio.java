package CineMais;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TelaGestaoCardapio extends JFrame {

    private JPanel mainPanel;
    private JButton btnAddItem;
    private JButton btnRemoveItem;
    private JButton btnUpdateItem;
    private JButton btnViewCardapio;
    private JButton btnVoltar; // Botão Voltar
    private JTable cardapioTable;
    private JScrollPane scrollPane;
    private JTextField txtItemName;
    private JTextField txtItemPrice;
    private JTextField txtItemSize;
    private DefaultTableModel tableModel;
    
    private Connection conn;

    public TelaGestaoCardapio() {
        initComponents();
        connectToDatabase();
        loadCardapio();
    }

    private void initComponents() {
        setTitle("Gestão de Cardápio");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Valor", "Volume"}, 0);
        cardapioTable = new JTable(tableModel);
        scrollPane = new JScrollPane(cardapioTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 1, 5, 5)); // Adicionado espaço para o botão Voltar

        btnAddItem = new JButton("Adicionar Item");
        btnRemoveItem = new JButton("Remover Item");
        btnUpdateItem = new JButton("Alterar Item");
        btnViewCardapio = new JButton("Ver Cardápio");
        btnVoltar = new JButton("Voltar"); // Inicializado o botão Voltar

        buttonsPanel.add(btnAddItem);
        buttonsPanel.add(btnRemoveItem);
        buttonsPanel.add(btnUpdateItem);
        buttonsPanel.add(btnViewCardapio);
        buttonsPanel.add(btnVoltar); // Adicionado o botão Voltar

        mainPanel.add(buttonsPanel, BorderLayout.EAST);

        // Item Details Panel
        JPanel itemDetailsPanel = new JPanel();
        itemDetailsPanel.setLayout(new GridLayout(3, 2, 5, 5));

        itemDetailsPanel.add(new JLabel("Nome:"));
        txtItemName = new JTextField();
        itemDetailsPanel.add(txtItemName);

        itemDetailsPanel.add(new JLabel("Valor:"));
        txtItemPrice = new JTextField();
        itemDetailsPanel.add(txtItemPrice);

        itemDetailsPanel.add(new JLabel("Volume:"));
        txtItemSize = new JTextField();
        itemDetailsPanel.add(txtItemSize);

        mainPanel.add(itemDetailsPanel, BorderLayout.NORTH);

        add(mainPanel);

        // Action Listeners
        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        btnRemoveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItem();
            }
        });

        btnUpdateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateItem();
            }
        });

        btnViewCardapio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCardapio();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaAdm telaAdm = new TelaAdm();
                TelaGestaoCardapio.this.setVisible(false);
                telaAdm.setVisible(true);
            }
        });
    }

    private void connectToDatabase() {
        try {
            conn = ConexaoDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadCardapio() {
        if (conn == null) return;
        
        tableModel.setRowCount(0); // Clear the table before loading new data
        
        String sql = "SELECT * FROM produtos_cinema";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            DecimalFormat df = new DecimalFormat("R$ #,##0.00"); // Formatar valores

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nome");
                double value = rs.getDouble("valor");
                String volume = rs.getString("volume");

                tableModel.addRow(new Object[]{id, name, df.format(value), volume}); // Formatando o valor
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar o cardápio.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addItem() {
        if (conn == null) return;
        
        String name = txtItemName.getText();
        String priceStr = txtItemPrice.getText();
        String volume = txtItemSize.getText();
        
        if (name.isEmpty() || priceStr.isEmpty() || volume.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String sql = "INSERT INTO produtos_cinema (nome, valor, volume) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, volume);
            stmt.executeUpdate();
            loadCardapio(); // Refresh the cardápio list
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar item.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeItem() {
        if (conn == null) return;
        
        int selectedRow = cardapioTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int itemId = (int) tableModel.getValueAt(selectedRow, 0);
        
        String sql = "DELETE FROM produtos_cinema WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
            loadCardapio(); // Refresh the cardápio list
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao remover item.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateItem() {
        if (conn == null) return;
        
        int selectedRow = cardapioTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item para alterar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int itemId = (int) tableModel.getValueAt(selectedRow, 0);
        String name = txtItemName.getText();
        String priceStr = txtItemPrice.getText();
        String volume = txtItemSize.getText();
        
        if (name.isEmpty() || priceStr.isEmpty() || volume.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String sql = "UPDATE produtos_cinema SET nome = ?, valor = ?, volume = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, volume);
            stmt.setInt(4, itemId);
            stmt.executeUpdate();
            loadCardapio(); // Refresh the cardápio list
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao alterar item.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaGestaoCardapio().setVisible(true);
            }
        });
    }
}
