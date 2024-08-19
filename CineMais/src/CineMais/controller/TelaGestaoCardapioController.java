package CineMais.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import CineMais.model.ConexaoDB;

public class TelaGestaoCardapioController {

    private Connection conn;

    public TelaGestaoCardapioController() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            conn = ConexaoDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadCardapio(DefaultTableModel tableModel) {
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
            JOptionPane.showMessageDialog(null, "Erro ao carregar o cardápio.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addItem(String name, String priceStr, String volume, DefaultTableModel tableModel) {
        if (conn == null) return;
        
        if (name.isEmpty() || priceStr.isEmpty() || volume.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String sql = "INSERT INTO produtos_cinema (nome, valor, volume) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, volume);
            stmt.executeUpdate();
            loadCardapio(tableModel); // Refresh the cardápio list
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao adicionar item.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeItem(int selectedRow, DefaultTableModel tableModel) {
        if (conn == null || selectedRow == -1) return;
        
        int itemId = (int) tableModel.getValueAt(selectedRow, 0);
        
        String sql = "DELETE FROM produtos_cinema WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
            loadCardapio(tableModel); // Refresh the cardápio list
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao remover item.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateItem(int selectedRow, String name, String priceStr, String volume, DefaultTableModel tableModel) {
        if (conn == null || selectedRow == -1) return;
        
        int itemId = (int) tableModel.getValueAt(selectedRow, 0);
        
        if (name.isEmpty() || priceStr.isEmpty() || volume.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String sql = "UPDATE produtos_cinema SET nome = ?, valor = ?, volume = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, volume);
            stmt.setInt(4, itemId);
            stmt.executeUpdate();
            loadCardapio(tableModel); // Refresh the cardápio list
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao alterar item.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
